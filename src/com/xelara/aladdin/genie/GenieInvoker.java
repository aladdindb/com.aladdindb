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
import com.xelara.aladdin.magiclamp.wishes.addunit.AddRemoteProcess;
import com.xelara.aladdin.magiclamp.wishes.addunit.RemoteProcess;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.xml.XML;

public class GenieInvoker {

	public final GeniesMap genies = new GeniesMap();
	
	private final int port;
	
	public GenieInvoker( int port ) {
		this.port = port;
	}
	
    public void start() {
		try ( var server = new ServerSocket( port ) ) {
            var pool = Executors.newFixedThreadPool( 20 );
			System.out.println("Genie invoker was properly started ;-)");
			while( true ) {
				pool.execute ( new SocketIO( server.accept (), this ) );
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
    	    			if( inputLine.equals ( MagicLamp.EOCMD ) )break;
    					req.append( inputLine + "\n" );
    	    		}

    	    		var reqStr = req.toString();
    				if( reqStr != null && !reqStr.trim().isEmpty()) {
    	    			System.out.println( reqStr );
    	    			XML.parse( reqStr, wishNode -> {
    	    				new BeforeWish(wishNode).getUnitGroupID( unitGroupID -> {
    							this.invoker.genies.getGenie( unitGroupID, genie -> {
    	    						genie.process( wishNode, resp -> {
    	        						out.println( resp 		);
    	        						out.println( MagicLamp.EOCMD );
    	        						loop.set( true );
    	    						});
    							});
    	    				});
    	    			});
    				} else {
    					out.println( MagicLamp.EOCMD );
    					throw new IOException();
    				}
    	    		
//    	    		this.receiveWish2( req.toString(), out);
//    				this.receiveWish(req.toString(), out,  wishModel -> {
//						this.invoker.genies.get( wishModel, genie -> {
//    						genie.process( wishModel, resp -> {
//        						out.println( resp 		);
//        						out.println( MagicLamp.EOCMD );
//        						loop.set( true );
//    						});
//						});
//    				});
    				
    	        }
    	        
    			System.out.println ( "Die Socket-Verbindung wurde vom Client beendet !!!" );
    	        
    		} catch (IOException e) {
    			System.out.println ( "Die Socket-Verbindung wurde unerwartet unterbrochen !!! " );
    			e.printStackTrace();
    		}
            System.out.println("Bin draußen !!!");
        }

//        private void receiveWish( String reqStr, PrintWriter out,  Consumer < WishModel > wishConsumer ) throws IOException {
//			if( reqStr != null && !reqStr.trim().isEmpty()) {
//    			System.out.println( reqStr );
//    			XML.parse( reqStr, wishNode -> {
//    				new WishModelParser().toModel( wishNode, wishConsumer );
//    			});
//			} else {
//				out.println( MagicLamp.EOCMD );
//				throw new IOException();
//			}
//        }

//        private void receiveWish2( String reqStr, PrintWriter out ) throws IOException {
//			if( reqStr != null && !reqStr.trim().isEmpty()) {
//    			System.out.println( reqStr );
//    			XML.parse( reqStr, wishNode -> {
//    				new BeforeWish(wishNode).getUnitGroupID( unitGroupID -> {
//						this.invoker.genies.getGenie( unitGroupID, genie -> {
//    						genie.process( wishNode, resp -> {
//        						out.println( resp 		);
//        						out.println( MagicLamp.EOCMD );
//        						loop.set( true );
//    						});
//						});
//    				});
//    			});
//			} else {
//				out.println( MagicLamp.EOCMD );
//				throw new IOException();
//			}
//        }
        
    }
    
    
}
