package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnValueType;
import com.xelara.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 */
public class TextModelParser extends DataModelParser < TextModel > {
    
    
	public TextModelParser() {
		super("Text");
	}	
	
    public TextModelParser( Enum<?> key ) {
        this( key.name());
    }
    
    public TextModelParser( String key ) {
        super( key );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public TextModel newModel() {
        return new TextModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public TextModel fromNode( SnPoint src, TextModel target ) {
        
        target.setValue( src.value.get() );
        
        return target;
    }
    
    
    @Override
    public SnPoint toNode( TextModel src, SnPoint target ) {
        
        src.getValue( target.value :: set );
        
        target.valueType.set( SnValueType.CDATA );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
