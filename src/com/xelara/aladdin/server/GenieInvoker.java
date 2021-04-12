package com.xelara.aladdin.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

import com.xelara.aladdin.client.Channel;
import com.xelara.core.util.Var;
import com.xelara.structure.xml.XML;

public class GenieInvoker {

	public final MagicLamp magicLamp = new MagicLamp();
	
	private final int port;
	
	public GenieInvoker( int port ) {
		this.port = port;
	}
	
    public void start() {
		try ( var serverSocket = new ServerSocket( port ) ) {
            var pool = Executors.newFixedThreadPool( 20 );
			System.out.println("Genie invoker was properly started ;-)");
			while( true ) {
				pool.execute ( new SocketIO( serverSocket.accept (), this ) );
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
    	
        private final GenieInvoker invoker;

        SocketIO( Socket socket, GenieInvoker invoker ) {
            this.socket 	= socket;
            this.invoker 	= invoker;
        }
        
        @Override
        public void run() {
            try ( 
                var in 	= new Scanner		( socket.getInputStream		()  		);
        		var out = new PrintWriter	( socket.getOutputStream	(), true 	); 
            ){
            	Var<Boolean> loop = new Var<>(true);
    	        while ( loop.get() ) {
    	        	loop.set( false );
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
    	    			if( inputLine.equals ( Channel.END_OF_DATA ) )break;
    					req.append( inputLine + "\n" );
    	    		}

    	    		var reqStr = req.toString();
    	    		
    				if( reqStr != null && !reqStr.trim().isEmpty()) {
    	    			System.out.println( reqStr );
    	    			XML.parse( reqStr, reqNode -> {
    	    				new ReqNode(reqNode).getUnitGroupID( unitGroupID -> {
    							this.invoker.magicLamp.getGenie( unitGroupID, genie -> {
    								genie.reqNode		.set( reqNode );
    								genie.respConsumer	.set( respStr -> {
    	        						out.println( respStr 		);
    	        						out.println( Channel.END_OF_DATA );
    	        						loop.set( true );
    								});
    								genie.run();
    							});
    	    				});
    	    			});
    				} else {
    					out.println( Channel.END_OF_DATA );
    					throw new IOException();
    				}
    	    		
    	        }
    	        
    			System.out.println ( "Die Socket-Verbindung wurde vom Client beendet !!!" );
    	        
    		} catch (IOException e) {
    			System.out.println ( "Die Socket-Verbindung wurde unerwartet unterbrochen !!! " );
    		}
            System.out.println("Bin draußen !!!");
        }
    }
    
}
