package com.xelara.aladdin.genie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.MagicLamp;
import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.magiclamp.model.WishModelParser;
import com.xelara.core.T4Consumer;
import com.xelara.structure.xml.XML;

public class GenieInvoker {

	private final int port;
	
	public GenieInvoker( int port ) {
		this.port = port;
	}
	
    public void invoke( GenieConversation process ) {
		System.out.println("Genie invoker was properly started ;-)");
		try ( var server = new ServerSocket( port ) ) {
            var pool = Executors.newFixedThreadPool( 20 );
			while( true ) {
				pool.execute ( new SocketIO( server.accept (), process ) );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
    }
    
    
    /**
     * Sobald ein Client mit Genie Verbindung aufgebaut hat, wird ein 
     * SocketIO Thread gestartet, und hält so lange die Verbindung und
     * Kommunikation, bis der Client wieder beendet wird.
     * 
     * @author Macit Kandemir
     *
     */
    private static class SocketIO  implements Runnable {

    	private final Socket socket;
    	
        private final GenieConversation conversation;

        SocketIO( Socket socket, GenieConversation conversation ) {
            this.socket 		= socket;
            this.conversation 	= conversation;
        }
        
        @Override
        public void run() {
            try ( 
                var in 	= new Scanner		( socket.getInputStream		()  		);
        		var out = new PrintWriter	( socket.getOutputStream	(), true 	); 
            ){
    	        while ( true ) {
    	        	/*
    	        	 * Befindet sich im Warte-Zustand, bis ein Wunsch angekommen ist.
    	        	 * Das bedeutet echtes warten. 
    	        	 */
    		        String 			inputLine;
    	    		StringBuilder 	req = new StringBuilder();

    	    		/*
    	    		 * Hier wird der angekommene Wunsch, Zeile für Zeile gelesen.
    	    		 * Erst wenn das Zeilen-Ende erreciht wird, kann der Wunsch
    	    		 * weiter-Verarbeitet werden.  
    	    		 */
    	    		while( in.hasNextLine () ) {
    	    			inputLine = in.nextLine ();
    	    			if( inputLine.equals ( MagicLamp.EOCMD ) )break;
    					req.append( inputLine + "\n" );
    	    		}
    				
    				String reqStr = req.toString();

    				if( reqStr != null && !reqStr.trim().isEmpty()) {
    		        	processStart( reqStr, resp -> {
    						out.println( resp 		);
    						out.println( MagicLamp.EOCMD );
    		        	});
    				} else {
    					out.println( MagicLamp.EOCMD );
    					break;
    				}
    	        }
    	        
    			System.out.println ( "Die Socket-Verbindung wurde vom Client beendet !!!" );
    	        
    		} catch (IOException e) {
    			System.out.println ( "Die Socket-Verbindung wurde unerwartet unterbrochen !!! " );
    			e.printStackTrace();
    		}
        }

        private void processStart( String wishStr, Consumer< String > respConsumer ) {
        	if( wishStr != null ) {
    			System.out.println( wishStr );
    			XML.parse( wishStr, wishNode -> {
    				new WishModelParser().parse( wishNode, wish -> {
						conversation.begin( wish, respConsumer );
//    					wish.section.getValue( section -> {
//    						wish.cmd.getValue( cmd -> {
//    							process.accept( wish, section, cmd, respConsumer );
//    						});
//    					});
    				});
    			});
        	}
        }
    }
    
}
