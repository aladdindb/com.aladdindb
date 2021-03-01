package com.xelara.aladdin.index.model;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnParser;

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
    public IndexModel fromNode( SnPoint node, IndexModel model ) {
    	var parse = new SnParser( node );
    	
        parse._str.get( ATR.refUnitID, model.refUnitID );
        
        return model;
    }
    
    
    @Override
    public SnPoint toNode( IndexModel model, SnPoint node ) {
        
    	var parse = new SnParser( node );
    	
        parse._str.set( ATR.refUnitID  	, model.refUnitID   );
        
        node.valueType.set( SnValueType.SINGLE_LINE );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
