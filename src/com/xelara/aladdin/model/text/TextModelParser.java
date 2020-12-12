package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class TextModelParser extends UnitModelParser < TextModel > {
    
    
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
    public TextModel parse( SNode src, TextModel target ) {
        
        target.setValue( src.getValue() );
        
        return target;
    }
    
    
    @Override
    public SNode parse( TextModel src, SNode target ) {
        
        src.getValue( target :: setValue );
        
        target.setValueType( SN.VALUE_TYPE_CDATA );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
