package com.aladdindb;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.xml.XML;


public class GenieConnection {
	
	public static final String END_OF_DATA 	="x394gh856osrel215yc4you719";
	
	private boolean isOpen = false;

	private Scanner			in;
	private PrintWriter		out;
	private Socket			socket;

	private final String 	host;
	private final int 		port;
	
	public GenieConnection() {
		this( "localhost" );
	}	
	
	public GenieConnection( String host ) {
		this( host, 7735 );
	}	

	public GenieConnection( String host, int port ) {
		this.host = host;
		this.port = port;
	}

	public boolean open() {
		System.out.print("\n Connection width ("+ host + " : " + port+") ");
		try {
			socket 	= new Socket		( host, port );
			out 	= new PrintWriter	( socket.getOutputStream(), true  );
			in 		= new Scanner		( socket.getInputStream() );
			this.isOpen = true;
			System.out.print(" was established successfully :-) \n");
		} catch ( IOException e ) {
			System.out.print(" couldn't be established :-( \n");
			close();
		}
		return this.isOpen;
	}
	
	public void close() {
		System.out.print ("\n Connecting closed with ("+ host + " : " + port+") " );
		try {
			if( out 	!= null) out	.close();
			if( in 		!= null) in		.close();
			if( socket 	!= null) socket	.close();
			System.out.print (" was properly :-) \n" );
		} catch (IOException e) {
			System.out.print (" couldn't be done properly :-( \n" );
		}
		this.isOpen = false;
	}
	
	public void sendReq( SnPoint reqNode,  Consumer < SnPoint > respNodeConsumer ) {
		XML.parse( reqNode, reqStr -> {
			this.sendReq( reqStr, respStr -> {
				System.out.println("\n"+ respStr+ "\n" );
				XML.parse( respStr, respNodeConsumer);
			});
		});
	}
	
	
	public void sendReq( String reqStr,  Consumer < String > respStrConsumer ) {
		if( !isOpen) this.open();
		if( isOpen ) {
			out.println( reqStr );
			out.println( END_OF_DATA );
			
			getRespStr( respStrConsumer );
		}
	}
	
	private void getRespStr( Consumer < String >  respStrConsumer ) {
		StringBuilder sb = new StringBuilder();
		String inLine, delim = "";
		while( in.hasNextLine () ) {
			inLine = in.nextLine ();
			if( inLine.equals ( END_OF_DATA ) ) break;
			sb.append( delim+inLine );
			delim = "\n";
		}
		var respStr = sb.toString();
		if( respStr != null && !respStr.trim().isEmpty() ) respStrConsumer.accept( respStr );
	}
	
	
}
