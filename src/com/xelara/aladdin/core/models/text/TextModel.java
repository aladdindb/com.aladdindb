package com.xelara.aladdin.core.models.text;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

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
