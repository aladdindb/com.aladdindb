package com.xelara.aladdin.core.models.server;

import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 */
public class ServerUnitParser extends DataParser < ServerUnit > {
    
    
    public enum ATR     { host, port };

    
    //****************************************************************
    //
    //****************************************************************

    public ServerUnitParser() {
        super( "Server" );
    }

    //****************************************************************
    //
    //****************************************************************

    @Override
    public ServerUnit newModel() {
        return new ServerUnit();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public ServerUnit toModel( SnPoint node, ServerUnit model ) {
    
    	var parse = new SnAttributeAccess( node );
    	
    	parse.asStr.get( ATR.host, model.host );
    	parse.asInt.get( ATR.port, model.port );
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( ServerUnit model, SnPoint node ) {
        
    	var parse = new SnAttributeAccess( node );
    	
    	parse.asStr.set( ATR.host , model.host );
    	parse.asInt.set( ATR.port , model.port );
        
        node.valueType.set( SnValueType.SINGLE_LINE );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
