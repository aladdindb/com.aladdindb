package com.aladdindb;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.aladdindb.method.req.ReqNode;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.util.Var;

public class GenieInvoker {

	
	public final HashMap < String , Genie < ? extends DataModel< ? > > > otherSide = new HashMap<>();
	
	private final int port;
	
	public GenieInvoker() {
		this( 7735 );
	}
	
	public GenieInvoker( int port ) {
		this.port = port;
	}
	
    public void invoke() {
		try ( var serverSocket = new ServerSocket( port ) ) {
            var pool = Executors.newFixedThreadPool( 20 );
			System.out.println("\n Genie invoker was properly started ;-) \n");
			while( true ) {
				System.out.println( "\n --------------------");
				System.out.println( " pool.execute Beginn ");
				System.out.println( " --------------------\n");
				pool.execute ( new SocketIO( serverSocket.accept (), this ) );
				System.out.println( "\n --------------------");
				System.out.println( " pool.execute End ");
				System.out.println( " --------------------\n");
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
            	Var<Boolean> loop = new Var<>( null, true);
            	
				System.out.println( "\n ----------------------");
            	System.out.println( "   Start SocketIO" );
				System.out.println( " ----------------------\n");
            	
    	        while ( loop.get() ) {
    	        	
//    				System.out.println( "\n ----------------------");
//    	        	System.out.println( "    loop-beginn" );
//    				System.out.println( " ----------------------\n");
    				
    	        	loop.set( false );
    	        	
    	    		this.getReqStr( in, reqStr -> {
    	    			System.out.print( reqStr );
    	    			
    	    			this.getGenie( reqStr, genie -> {
    	    				
							genie.respConsumer.set( respStr -> {
        						out.println( respStr 		);
        						out.println( GenieConnection.END_OF_DATA );
        						loop.set( true );
							});
							
							genie.run();
							
    	    			});
    	    		});
    				
    	        }
    	        
				System.out.println( "\n ----------------------");
            	System.out.println( "     Stop SocketIO" );
				System.out.println( " ----------------------\n");
    	        
    		} catch ( IOException e ) {
    			System.out.println ( "\n SocketIO-Exception :-( \n" );
    		}
            
            System.out.println("\n By by ;-) \n");
        }
        
		/*
		 * Hier wird das angekommene req, Zeile für Zeile gelesen.
		 * Erst wenn das Zeilen-Ende erreciht wird, kann das req
		 * weiter-Verarbeitet werden.  
		 */
        private void getReqStr( Scanner in,  Consumer < String > reqStrConsumer ) {
    		StringBuilder 	req = new StringBuilder();
    		String 			inLine;
    		while( in.hasNextLine () ) {
    			inLine = in.nextLine ();
    			if( inLine.equals ( GenieConnection.END_OF_DATA ) )break;
				req.append( inLine + "\n" );
    		}
    		var reqStr = req.toString();
			if( reqStr != null && !reqStr.trim().isEmpty()) reqStrConsumer.accept( reqStr );
        }
        
        private void getGenie( String reqStr, Consumer < Genie<?> > genieConsumer ) {
			XML.toNode( reqStr, reqNode -> {
				new ReqNode(reqNode).getStoreId( storeId -> {
					this.invoker.getGenie( storeId, genie -> {
						genie.reqNode.set( reqNode );
						genieConsumer.accept( genie );
					});
				});
			});
        }
        
    }
    
    public void getGenie( String unitGroupID, Consumer< Genie< ? extends DataModel< ? > > > genieConsumer ) {
    	var server = this.otherSide.get(unitGroupID);
    	if( server != null ) genieConsumer.accept( server );  
    }
    
    public void putGenie( Genie< ? extends DataModel< ? > > genie ) {
    	this.otherSide.put( genie.storeId, genie );
    }
    
    
}
