package com.aladdindb.models.text;

import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class LabelModel extends Var< String > implements Store < LabelModel > {
    
    public LabelModel() {
    }
    
    public LabelModel( String label ) {
    	this.set( label );
    }
    
    public void fill( LabelModel model ) {
    	this.set( model );
    }
    
}
