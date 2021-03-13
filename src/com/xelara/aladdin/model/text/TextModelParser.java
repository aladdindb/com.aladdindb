package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;

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
    public TextModel toModel( SnPoint src, TextModel target ) {
        
        target.set( src.value.get() );
        
        return target;
    }
    
    
    @Override
    public SnPoint toNode( TextModel src, SnPoint target ) {
        
        src.get( target.value :: set );
        
        target.valueType.set( SnValueType.CDATA );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
