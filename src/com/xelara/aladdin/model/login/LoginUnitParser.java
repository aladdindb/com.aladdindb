package com.xelara.aladdin.model.login;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnParser;

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
    
    	var parse = new SnParser( node );
    	
    	parse._str.get( ATR.user		,model.user 	);
    	parse._str.get( ATR.pwd		,model.pwd 		);
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( LoginModel model, SnPoint node ) {
        
    	var parse = new SnParser( node );
    	
    	parse._str.set( ATR.user		,model.user 	);
    	parse._str.set( ATR.pwd  	,model.pwd    	);
        
        node.valueType.set( SnValueType.SINGLE_LINE );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
