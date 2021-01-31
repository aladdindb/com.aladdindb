package com.xelara.aladdin.magiclamp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.magiclamp.model.WishModelParser;
import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.xml.XML;

/**
 *
 * @author Macit
 */
public  class MagicLamp < DATA_MODEL extends DataModel < DATA_MODEL > > {

	public static final String GET_BY_ID	="GET_BY_ID";
	public static final String GET_ALL		="GET_ALL";
	public static final String ADD			="ADD";
	public static final String UPDATE		="UPDATE";
	public static final String REMOVE		="REMOVE";
	
	public static final String BOCMD		="x394gh856osrel215yc4you319";
	public static final String EOCMD		="x394gh856osrel215yc4you719";
	
	private final Connection connection;
	
    private static MagicLampSocket socket = null;
	
	public final	UnitModelParser< DATA_MODEL >	unitModelParser;
	
	public final 	String 				invokeID;
	private 		Monitor 			wishMonitor;
	
	public MagicLamp(  String invokeID, DataModelParser< DATA_MODEL > dataModelParser, Connection connection ) {
		this.invokeID 			= invokeID;
		this.unitModelParser 	= new UnitModelParser< DATA_MODEL>(dataModelParser);
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

    public void addUnit( DATA_MODEL dataModel, Consumer< String > consumer ) {
    	var wish = createWish( ADD, "" );
    	var unitModel = new UnitModel< DATA_MODEL >();
    	unitModel.data.setValue( dataModel );
    	unitModelParser.toNode( unitModel, unitModelNode -> {
    		XML.parse( unitModelNode, wish.object :: setValue );
    	});
    	execWish( wish, consumer );
    }
    
    public void updateUnit( UnitModel< DATA_MODEL> unitModel, Consumer< String > consumer ) {
    	var wish = createWish( UPDATE, "" );
    	unitModelParser.toNode( unitModel, unitNode -> {
    		XML.parse( unitNode, wish.object :: setValue );
    	});
    	execWish( wish, consumer );
    }

    public void removeUnit( String unitID, Consumer< String > consumer ) {
    	var wish = createWish( REMOVE, unitID );
    	execWish( wish, consumer );
    }
    
    public void forEachUnit( Consumer< UnitModel< DATA_MODEL> > consumer ) {
        var wish = createWish( GET_ALL, "" );
        forEachUnit(wish, unitNode -> {
           	consumer.accept( unitModelParser.fromNode( unitNode ) );
        });
    }
    
    public void getUnit( String unitID, Consumer< UnitModel< DATA_MODEL> > consumer ) {
        var wish = createWish( GET_BY_ID, unitID );
        getUnit( wish, unitNode -> {
        	unitModelParser.fromNode( unitNode, consumer );
        });
    }
    
    //************************************************************
    //					
    //************************************************************
    
    public Map< String, String > createIdLabelMap() {
        Map< String, String > rv = new HashMap<>();
        var wish = createWish(  GET_ALL, "" );
        forEachUnit( wish, unitNode -> {
            var unitID      = unitNode.attributeLine.getValue( "id"       );
            var unitLabel 	= unitNode.attributeLine.getValue( "label"    );
            rv.put( unitID, unitLabel );
        });
        return rv;
    }
    
	public void forEachUnit( WishModel wish, Consumer< SnPoint > consumer) {
		execWish( wish, resp -> {
	     	XML.parse( resp, respNode -> {
	     		respNode.deepLine.forEach(consumer);
	    	});
		});
	}
	
	public void getUnit( WishModel wish, Consumer< SnPoint > consumer) {
		execWish( wish, resp -> {
	     	XML.parse( resp, respNode -> {
	     		respNode.deepLine.start.get( consumer );
	    	});
		});
	}
    
    public void execWish( WishModel wish, Consumer < String > respConsumer ) {
    	wish.userID.setValue (  connection.userID );
    	wishMonitor.monitoring( () -> {
    		getSocket( magicLampSocket -> {
    			new WishModelParser().toNode( wish, wishNode -> {
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
        return new WishModel( this.invokeID, cmd, sbj );
    }
    
}
