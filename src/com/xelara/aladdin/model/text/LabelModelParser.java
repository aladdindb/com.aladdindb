package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.node.Snode;
import com.xelara.structure.node.SnValueType;

/**
 *
 * @author Macit Kandemir
 */
public class LabelModelParser extends DataModelParser < LabelModel > {
    
    
	public LabelModelParser() {
		super("label");
	}	
	
    public LabelModelParser( Enum<?> key ) {
        this( key.name());
    }
    
    public LabelModelParser( String key ) {
        super( key );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LabelModel newModel() {
        return new LabelModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LabelModel fromNode( Snode src, LabelModel target ) {
        
        target.setValue( src.value.get() );
        
        return target;
    }
    
    
    @Override
    public Snode toNode( LabelModel src, Snode target ) {
        
        src.getValue( target.value :: set );
        
        target.setValueType( SnValueType.SL_CLOSE );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
