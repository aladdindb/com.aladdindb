package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.node.Snode;
import com.xelara.structure.node.SnValueType;

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
    public TextModel fromNode( Snode src, TextModel target ) {
        
        target.setValue( src.value.get() );
        
        return target;
    }
    
    
    @Override
    public Snode toNode( TextModel src, Snode target ) {
        
        src.getValue( target.value :: set );
        
        target.setValueType( SnValueType.CDATA );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
