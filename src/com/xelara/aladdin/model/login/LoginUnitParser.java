package com.xelara.aladdin.model.login;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnValueType;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.AParser;

/**
 *
 * @author Macit Kandemir
 */
public class LoginUnitParser extends DataModelParser < LoginModel > {
    
    
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
    public LoginModel fromNode( SnPoint node, LoginModel model ) {
    
    	var parse = new AParser( node );
    	
    	parse.strPrs.get( ATR.user		,model.user 	);
    	parse.strPrs.get( ATR.pwd		,model.pwd 		);
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( LoginModel model, SnPoint node ) {
        
    	var parse = new AParser( node );
    	
    	parse.strPrs.set( ATR.user		,model.user 	);
    	parse.strPrs.set( ATR.pwd  	,model.pwd    	);
        
        node.valueType.set( SnValueType.SINGLE_LINE );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
