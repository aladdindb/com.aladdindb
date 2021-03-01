package com.xelara.aladdin.model.server;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnParser;

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
    
    	var parse = new SnParser( node );
    	
    	parse._str.get( ATR.host, model.host );
    	parse._int.get( ATR.port, model.port );
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( ServerUnit model, SnPoint node ) {
        
    	var parse = new SnParser( node );
    	
    	parse._str.set( ATR.host , model.host );
    	parse._int.set( ATR.port , model.port );
        
        node.valueType.set( SnValueType.SINGLE_LINE );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
