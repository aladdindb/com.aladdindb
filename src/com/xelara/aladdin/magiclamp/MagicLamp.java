package com.xelara.aladdin.magiclamp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.magiclamp.model.WishModelParser;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.aladdin.unit.model.DbUnitModelParser;
import com.xelara.structure.snode.SNode;
import com.xelara.structure.xml.XML;

/**
 *
 * @author Macit
 */
public  class MagicLamp <

		DUM		extends DbUnitModel 		< DUM >,
		DUMP 	extends DbUnitModelParser	< DUM >

	> {

	public static final String GET_BY_ID	="GET_BY_ID";
	public static final String GET_ALL		="GET_ALL";
	public static final String ADD			="ADD";
	public static final String UPDATE		="UPDATE";
	public static final String REMOVE		="REMOVE";
	
	public static final String BOCMD		="x394gh856osrel215yc4you319";
	public static final String EOCMD		="x394gh856osrel215yc4you719";
	
	private final MagicLampConnectionData connection;
	
    private static MagicLampSocket socket = null;
	
	public final	DUMP 				unitModelParser;
	public final 	String 				section;
	private 		Monitor 			wishMonitor;
	
	public MagicLamp(  String section, DUMP unitModelParser, MagicLampConnectionData connection ) {
		this.section 			= section;
		this.unitModelParser 	= unitModelParser;
		this.wishMonitor 		= new MonitorDefault();
		
		this.connection = connection;
    }
    
    //************************************************************
    //					
    //************************************************************

    public void getSocket( Consumer< MagicLampSocket > consumer ) {
		if(socket == null) socket = new MagicLampSocket( connection.host, connection.port );
		consumer.accept(socket);
	}
	
	public static final boolean isNotEOF( String str ) {
		return str != null && !str.equals( EOCMD ); 
	}
    
    //************************************************************
    //					
    //************************************************************

    public void addUnit( DUM unitModel, Consumer< String > consumer ) {
    	var wish = createWish( ADD, "" );
    	unitModelParser.parse( unitModel, unitNode -> {
    		XML.parse( unitNode, wish.object :: setValue );
    	});
    	execWish( wish, consumer );
    }
    
    public void updateUnit( DUM unitModel, Consumer< String > consumer ) {
    	var wish = createWish( UPDATE, "" );
    	unitModelParser.parse( unitModel, unitNode -> {
    		XML.parse( unitNode, wish.object :: setValue );
    	});
    	execWish( wish, consumer );
    }

    public void removeUnit( String unitID, Consumer< String > consumer ) {
    	var wish = createWish( REMOVE, unitID );
    	execWish( wish, consumer );
    }
    
    public void forEachUnit( Consumer< DUM > consumer ) {
        var wish = createWish( GET_ALL, "" );
        forEachUnit(wish, unitNode -> {
           	consumer.accept( unitModelParser.parse( unitNode ) );
        });
    }
    
    public void getUnit( String unitID, Consumer<DUM> consumer ) {
        var wish = createWish( GET_BY_ID, unitID );
        getUnit( wish, unitNode -> {
        	unitModelParser.parse( unitNode, consumer );
        });
    }
    
    //************************************************************
    //					
    //************************************************************
    
    public Map< String, String > createIdLabelMap() {
        Map< String, String > rv = new HashMap<>();
        var wish = createWish(  GET_ALL, "" );
        forEachUnit( wish, unitNode -> {
            var unitID      = unitNode.getAttribute( "id"       );
            var unitLabel 	= unitNode.getAttribute( "label"    );
            rv.put( unitID, unitLabel );
        });
        return rv;
    }
    
	public void forEachUnit( WishModel wish, Consumer< SNode > consumer) {
		execWish( wish, resp -> {
	     	XML.parse( resp, respNode -> {
	     		respNode.forEachChilds(consumer);
	    	});
		});
	}
	
	public void getUnit( WishModel wish, Consumer< SNode > consumer) {
		execWish( wish, resp -> {
	     	XML.parse( resp, respNode -> {
	     		SNode childNode = respNode.getFirstChild();
	     		if( childNode != null ) consumer.accept(childNode);
	    	});
		});
	}
    
    public void execWish( WishModel wish, Consumer < String > respConsumer ) {
    	wish.userID.setValue (  connection.userID );
    	wishMonitor.monitoring( () -> {
    		getSocket( magicLampSocket -> {
    			new WishModelParser().parse( wish, wishNode -> {
    				XML.parse( wishNode,  wishStr -> {
    					System.out.println("Magic-Lamp:");
    					System.out.println( wishStr );
    					String resp = magicLampSocket.sendWish( wishStr );
    					if( resp != null )respConsumer.accept(resp);
    				});
    			});
    		});
    	});
    }
    
    public WishModel createWish( String cmd , String sbj ) {
        return new WishModel( this.section, cmd, sbj );
    }
    
}
