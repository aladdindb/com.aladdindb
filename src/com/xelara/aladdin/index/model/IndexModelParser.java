package com.xelara.aladdin.index.model;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.node.Snode;
import com.xelara.structure.attributes.AParser;
import com.xelara.structure.node.SnValueType;

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
    public IndexModel fromNode( Snode node, IndexModel model ) {
    	var parse = new AParser( node );
    	
        parse.strPrs.get( ATR.refUnitID, model.refUnitID );
        
        return model;
    }
    
    
    @Override
    public Snode toNode( IndexModel model, Snode node ) {
        
    	var parse = new AParser( node );
    	
        parse.strPrs.set( ATR.refUnitID  	, model.refUnitID   );
        
        node.setValueType( SnValueType.SL_VOID );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
