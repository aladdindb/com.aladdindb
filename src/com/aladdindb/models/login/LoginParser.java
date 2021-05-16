package com.aladdindb.models.login;

import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 */
public class LoginParser extends Transformer < LoginModel > {
    
    
    public enum ATR     { user, pwd };

    
    //****************************************************************
    //
    //****************************************************************

    public LoginParser() {
        super( "login" );
    }

    //****************************************************************
    //
    //****************************************************************

    @Override
    public LoginModel newStore() {
        return new LoginModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LoginModel toStore( SnPoint node, LoginModel model ) {
    
    	var parse = new SnAttributeAccess( node );
    	
    	parse.asStr.get( ATR.user		,model.user 	);
    	parse.asStr.get( ATR.pwd		,model.pwd 		);
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( LoginModel model, SnPoint node ) {
        
    	var parse = new SnAttributeAccess( node );
    	
    	parse.asStr.set( ATR.user		,model.user 	);
    	parse.asStr.set( ATR.pwd  	,model.pwd    	);
        
        node.valueType.set( SnValueType.SINGLE_LINE );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
