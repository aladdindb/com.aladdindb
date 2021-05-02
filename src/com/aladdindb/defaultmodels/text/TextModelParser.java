package com.aladdindb.defaultmodels.text;

import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;

/**
 *
 * @author Macit Kandemir
 */
public class TextModelParser extends DataTransformer < TextModel > {
    
    
	public TextModelParser() {
		super("text");
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
        
        src.value.get( target :: set );
        
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
