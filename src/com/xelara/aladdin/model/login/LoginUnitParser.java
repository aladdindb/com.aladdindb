package com.xelara.aladdin.model.login;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class LoginUnitParser extends DataModelParser < LoginUnit > {
    
    
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
    public LoginUnit newModel() {
        return new LoginUnit();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LoginUnit parse( SNode src, LoginUnit target ) {
    
        Parser.STR.parse(ATR.user, src    ,target.user );
        Parser.STR.parse(ATR.pwd, src     ,target.pwd );
        
        return target;
    }
    
    
    @Override
    public SNode parse( LoginUnit src, SNode target ) {
        
        Parser.STR.parse(ATR.user ,src.user   ,target );
        Parser.STR.parse(ATR.pwd  ,src.pwd    ,target );
        
        target.setValueType( SN.VALUE_TYPE_SL_VOID );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
