package com.aladdindb.models.text;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class UnitLabel extends Var< String > implements DataModel < UnitLabel > {
    
    public UnitLabel() {
    }
    
    public UnitLabel( String label ) {
    	this.set( label );
    }
    
    public void fill( UnitLabel model ) {
    	this.set( model );
    }
    
}
