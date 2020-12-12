package com.xelara.aladdin.model.list.selection;

import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 * @param <PROP>
 */
public class SelectionModelParser  extends UnitModelParser < SelectionModel > {

    public enum ATR { refUnitID };
    
	public SelectionModelParser() {
		super("Item");
	}
	
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SelectionModel newModel() {
        return  new SelectionModel (null);
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SelectionModel  parse( SNode src, SelectionModel target ) {
    
        Parser.STR.parse( src, ATR.refUnitID ,target.refUnitID );
        
        return target;
    }
    
    @Override
    public SNode parse( SelectionModel src, SNode target ) {
        
        Parser.STR.parse( src.refUnitID , ATR.refUnitID ,target );

        target.setValueType( SN.VALUE_TYPE_SL_VOID );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
