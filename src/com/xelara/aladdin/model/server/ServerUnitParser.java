package com.xelara.aladdin.model.server;

import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class ServerUnitParser extends UnitModelParser < ServerUnit > {
    
    
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
    public ServerUnit parse( SNode src, ServerUnit target ) {
    
        Parser.STR.parse( src, ATR.host   ,target.host );
        Parser.INT.parse( src, ATR.port   ,target.port );
        
        return target;
    }
    
    
    @Override
    public SNode parse( ServerUnit src, SNode target ) {
        
        Parser.STR.parse( src.host , ATR.host ,target );
        Parser.INT.parse( src.port , ATR.port ,target );
        
        target.setValueType( SN.VALUE_TYPE_SL_VOID );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
