package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.Var;

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
    	super.fill( unit );
    }
    
}
