package com.xelara.aladdin.core.models.text;

import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;

/**
 *
 * @author Macit Kandemir
 */
public class LabelModelParser extends DataParser < LabelModel > {
    
    
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
    public LabelModel toModel( SnPoint src, LabelModel target ) {
        
        target.set( src.value.get() );
        
        return target;
    }
    
    
    @Override
    public SnPoint toNode( LabelModel src, SnPoint target ) {
        
        src.get( target.value :: set );
        
        target.valueType.set( SnValueType.SINGLE_LINE_CLOSE );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
