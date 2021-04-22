package com.xelara.aladdin.core.models.text;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;

/**
 *
 * @author Macit Kandemir
 */
public class TextModel extends Var< String > implements DataModel < TextModel > {
    
    public TextModel() {
    }
    
    public TextModel( String text ) {
    	this.set(text);
    }
    
    public void fill( TextModel unit ) {
    	super.set( unit );
    }
    
}
