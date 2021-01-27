package com.xelara.aladdin.model.server;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.node.Snode;
import com.xelara.structure.attributes.AParser;
import com.xelara.structure.node.SnValueType;

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
    public ServerUnit fromNode( Snode node, ServerUnit model ) {
    
    	var parse = new AParser( node );
    	
    	parse.strPrs.get( ATR.host, model.host );
    	parse.intPrs.get( ATR.port, model.port );
        
        return model;
    }
    
    
    @Override
    public Snode toNode( ServerUnit model, Snode node ) {
        
    	var parse = new AParser( node );
    	
    	parse.strPrs.set( ATR.host , model.host );
    	parse.intPrs.set( ATR.port , model.port );
        
        node.setValueType( SnValueType.SL_VOID );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
