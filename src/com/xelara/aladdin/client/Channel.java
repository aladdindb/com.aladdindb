package com.xelara.aladdin.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Channel {
	
//	public static final String BOR 		="x394gh856osrel215yc4you319";
	public static final String END_OF_DATA 	="x394gh856osrel215yc4you719";
	
	public Scanner			in;
	public PrintWriter		out;
	public Socket			socket;

	public Channel( String host, int port ) {
		try {
			System.out.println ( host + " : " + port );
			socket 	= new Socket		( host, port );
			out 	= new PrintWriter	( socket.getOutputStream(), true  );
			in 		= new Scanner		( socket.getInputStream() );
		} catch (IOException e) {
			stop();
			Logger.getLogger( Channel.class.getName()).log( Level.SEVERE, "", e );
		}
	}

	
	public void sendReq( String reqStr,  Consumer<String> respConsumer ) {
		out.println( reqStr );
		out.println( END_OF_DATA );
		
		getRespStr( respConsumer );
	}
	
	private void getRespStr( Consumer < String >  consumer ) {
		StringBuilder sb = new StringBuilder();
		String inLine, delim = "";
		while( in.hasNextLine () ) {
			inLine = in.nextLine ();
			if( inLine.equals ( END_OF_DATA ) ) break;
			sb.append( delim+inLine );
			delim = "\n";
		}
		var respStr = sb.toString();
		if( respStr != null && !respStr.trim().isEmpty() ) consumer.accept( respStr );
	}
	
	public void stop() {
		try {
			if( out 	!= null) out	.close();
			if( in 		!= null) in		.close();
			if( socket 	!= null) socket	.close();
		} catch (IOException e) {
			Logger.getLogger( Channel.class.getName()).log( Level.SEVERE, "", e );
		}
	}
	
	
}
