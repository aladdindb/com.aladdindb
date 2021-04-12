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
			socket 	= new Socket( host, port );
			out 	= new PrintWriter( socket.getOutputStream(), true  );
			in 		= new Scanner( socket.getInputStream() );
		} catch (IOException e) {
			stop();
			Logger.getLogger( Channel.class.getName()).log( Level.SEVERE, "", e );
		}
	}

	
	public void sendReq( String reqStr, Consumer<String> respConsumer ) {
		var rv = this.sendReq(reqStr);
		if( rv != null && !rv.trim().isEmpty() )respConsumer.accept( rv );
	}
	
	public String sendReq( String reqStr ) {
		
		StringBuilder resp = new StringBuilder();
		
		out.println( reqStr );
		out.println( END_OF_DATA );

		String inputLine, delim = "";
		
		while( in.hasNextLine () ) {
			inputLine = in.nextLine ();
			if( inputLine.equals ( END_OF_DATA ) ) break;
			resp.append( delim+inputLine );
			delim = "\n";
		}
		
		return resp.toString();
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
