package com.xelara.aladdin.core.models.login;

import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 */
public class LoginUnitParser extends DataParser < LoginModel > {
    
    
    public enum ATR     { user, pwd };

    
    //****************************************************************
    //
    //****************************************************************

    public LoginUnitParser() {
        super( "Login" );
    }

    //****************************************************************
    //
    //****************************************************************

    @Override
    public LoginModel newModel() {
        return new LoginModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LoginModel toModel( SnPoint node, LoginModel model ) {
    
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
