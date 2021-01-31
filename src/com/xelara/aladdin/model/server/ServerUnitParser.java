package com.xelara.aladdin.model.server;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnValueType;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.AParser;

/**
 *
 * @author Macit Kandemir
 */
public class ServerUnitParser extends DataModelParser < ServerUnit > {
    
    
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
    public ServerUnit fromNode( SnPoint node, ServerUnit model ) {
    
    	var parse = new AParser( node );
    	
    	parse.strPrs.get( ATR.host, model.host );
    	parse.intPrs.get( ATR.port, model.port );
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( ServerUnit model, SnPoint node ) {
        
    	var parse = new AParser( node );
    	
    	parse.strPrs.set( ATR.host , model.host );
    	parse.intPrs.set( ATR.port , model.port );
        
        node.valueType.set( SnValueType.SINGLE_LINE );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
