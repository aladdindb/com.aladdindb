package com.aladdindb.models.text;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Parent;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class UnitLabel extends Var< String > implements DataModel < UnitLabel > {
    
    public UnitLabel( Parent parent ) {
    	super( null, parent );
    }
    
    public UnitLabel( Parent parent, String label ) {
    	super( label, parent );
    }
    
    public void fill( UnitLabel model ) {
    	this.set( model );
    }
    
}
