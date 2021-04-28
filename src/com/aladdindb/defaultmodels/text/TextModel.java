package com.aladdindb.defaultmodels.text;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

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
