package com.aladdindb.models.text;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Parent;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class TextModel extends Var< String > implements DataModel < TextModel > {

	
    public TextModel( Parent parent ) {
    	this( parent, null );
    }
    
    public TextModel( Parent parent, String text ) {
    	super( parent );
    	this.set(text);
    }
    
    public void fill( TextModel unit ) {
    	this.set( unit );
    }
    
}
