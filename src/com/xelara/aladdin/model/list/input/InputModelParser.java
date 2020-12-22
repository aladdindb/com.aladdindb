package com.xelara.aladdin.model.list.input;

import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 * @param <PROP>
 */
public class InputModelParser  extends UnitModelParser < InputModel > {

    public enum ATR { entry };
    
	public InputModelParser() {
		super("Item");
	}
	
    //****************************************************************
    //
    //****************************************************************

    @Override
    public InputModel newModel() {
        return  new InputModel (null);
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public InputModel  parse( SNode src, InputModel target ) {
    
        Parser.STR.parse( ATR.entry, src ,target.entry );
        
        return target;
    }
    
    @Override
    public SNode parse( InputModel src, SNode target ) {
        
        Parser.STR.parse( ATR.entry , src.entry ,target );

        target.setValueType( SN.VALUE_TYPE_SL_VOID );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
