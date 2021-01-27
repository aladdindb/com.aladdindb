package com.xelara.aladdin.model.login;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.node.Snode;
import com.xelara.structure.attributes.AParser;
import com.xelara.structure.node.SnValueType;

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
    public LoginModel fromNode( Snode node, LoginModel model ) {
    
    	var parse = new AParser( node );
    	
    	parse.strPrs.get( ATR.user		,model.user 	);
    	parse.strPrs.get( ATR.pwd		,model.pwd 		);
        
        return model;
    }
    
    
    @Override
    public Snode toNode( LoginModel model, Snode node ) {
        
    	var parse = new AParser( node );
    	
    	parse.strPrs.set( ATR.user		,model.user 	);
    	parse.strPrs.set( ATR.pwd  	,model.pwd    	);
        
        node.setValueType( SnValueType.SL_VOID );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
