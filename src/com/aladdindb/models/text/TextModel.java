package com.aladdindb.models.text;

import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class TextModel extends Var< String > implements Store < TextModel > {
    
    public TextModel() {
    }
    
    public TextModel( String text ) {
    	this.set(text);
    }
    
    public void fill( TextModel unit ) {
    	this.set( unit );
    }
    
}
