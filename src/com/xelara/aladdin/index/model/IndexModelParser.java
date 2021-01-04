package com.xelara.aladdin.index.model;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class IndexModelParser extends DataModelParser < IndexModel > {
    
    
    public enum ATR { refUnitID };

    
    //****************************************************************
    //
    //****************************************************************

    public IndexModelParser() {
        super( "Item" );
    }

    //****************************************************************
    //
    //****************************************************************

    @Override
    public IndexModel newModel() {
        return new IndexModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public IndexModel parse( SNode src, IndexModel target ) {
    
        Parser.STR.parse( ATR.refUnitID, src  	,target.refUnitID 	);
        
        return target;
    }
    
    
    @Override
    public SNode parse( IndexModel src, SNode target ) {
        
        Parser.STR.parse( ATR.refUnitID  	, src.refUnitID  		,target );
        
        target.setValueType( SN.VALUE_TYPE_SL_VOID );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
