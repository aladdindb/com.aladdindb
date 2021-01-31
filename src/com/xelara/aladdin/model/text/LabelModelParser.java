package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnValueType;
import com.xelara.structure.sn.SnPoint;

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
    public LabelModel fromNode( SnPoint src, LabelModel target ) {
        
        target.setValue( src.value.get() );
        
        return target;
    }
    
    
    @Override
    public SnPoint toNode( LabelModel src, SnPoint target ) {
        
        src.getValue( target.value :: set );
        
        target.valueType.set( SnValueType.SINGLE_LINE_CLOSE );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
