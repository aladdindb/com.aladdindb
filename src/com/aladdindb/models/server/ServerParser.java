package com.aladdindb.models.server;

import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 */
public class ServerParser extends Transformer < ServerModel > {
    
    
    public enum ATR     { host, port };

    
    //****************************************************************
    //
    //****************************************************************

    public ServerParser() {
        super( "server" );
    }

    //****************************************************************
    //
    //****************************************************************

    @Override
    public ServerModel newModel() {
        return new ServerModel( null );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public ServerModel toModel( SnPoint node, ServerModel model ) {
    
    	var parse = new SnAttributeAccess( node );
    	
    	parse.asStr.get( ATR.host, model.host );
    	parse.asInt.get( ATR.port, model.port );
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( ServerModel model, SnPoint node ) {
        
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
