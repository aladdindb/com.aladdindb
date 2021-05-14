package com.aladdindb.models.text;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class LabelModel extends Var< String > implements DataModel < LabelModel > {
    
    public LabelModel() {
    }
    
    public LabelModel( String label ) {
    	this.set( label );
    }
    
    public void fill( LabelModel model ) {
    	this.set( model );
    }
    
}
