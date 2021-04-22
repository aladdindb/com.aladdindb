package com.xelara.aladdin.core.models.text;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;

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
    	super.set( model );
    }
    
}
