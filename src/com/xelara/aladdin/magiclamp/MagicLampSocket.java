package com.xelara.aladdin.magiclamp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MagicLampSocket {
	
	public Scanner			in;
	public PrintWriter		out;
	public Socket			socket;

	public MagicLampSocket( String host, int port ) {
		try {
			System.out.println ( host + " : " + port );
			socket 	= new Socket( host, port );
			out 	= new PrintWriter( socket.getOutputStream(), true  );
			in 		= new Scanner( socket.getInputStream() );
		} catch (IOException e) {
			stop();
			Logger.getLogger( MagicLampSocket.class.getName()).log( Level.SEVERE, "", e );
		}
	}

	public String sendWish( String wishStr ) {
		
		StringBuilder resp = new StringBuilder();
		
		out.println( wishStr );
		out.println( MagicLamp.EOCMD );

		String inputLine, delim = "";
		
		while( in.hasNextLine () ) {
			inputLine = in.nextLine ();
			if( inputLine.equals ( MagicLamp.EOCMD ) )break;
			resp.append( delim+inputLine );
			delim = "\n";
		}

		return resp.toString();
	}
	
	
	public void stop() {
		try {
			out		.close();
			in		.close();
			socket	.close();
		} catch (IOException e) {
			Logger.getLogger( MagicLampSocket.class.getName()).log( Level.SEVERE, "", e );
		}
	}
	
	
}
