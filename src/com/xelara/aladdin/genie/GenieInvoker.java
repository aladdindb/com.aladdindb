package com.xelara.aladdin.genie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.MagicLamp;
import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.magiclamp.model.WishModelParser;
import com.xelara.aladdin.unit.model.DbUnitListModelParserDefault;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.aladdin.unit.model.DbUnitModelParser;
import com.xelara.core.T4Consumer;
import com.xelara.structure.xml.XML;

public class GenieInvoker {


	private final HashMap < String , Genie < ? extends DbUnitModel< ? > , ? extends DbUnitModelParser< ? > > >  genies = new HashMap <>();
	
	private final int port;
	
	public GenieInvoker( int port ) {
		this.port = port;
	}
	
    public void invoke() {
		System.out.println("Genie invoker was properly started ;-)");
		try ( var server = new ServerSocket( port ) ) {
            var pool = Executors.newFixedThreadPool( 20 );
			while( true ) {
				pool.execute ( new SocketIO( server.accept (), this ) );
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
    }
    
    public void putGenie( String key , Genie < ? extends DbUnitModel< ? > , ? extends DbUnitModelParser< ? > > genie ) {
    	this.genies.put( key, genie );
    }
    
    public Genie < ? extends DbUnitModel< ? > , ? extends DbUnitModelParser< ? > > getGenie( String key) {
    	return this.genies.get(key);
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
    	
        private final GenieInvoker conversation;

        SocketIO( Socket socket, GenieInvoker conversation ) {
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
    				new WishModelParser().parse( wishNode, wishModel -> {
    					wishModel.section.getValue( section -> {
    						var genie = this.conversation.getGenie( section );
    						if( genie != null) genie.process( wishModel, respConsumer );
    					});
    				});
    			});
        	}
        }
    }
    
}
