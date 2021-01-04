package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public class LabelModel extends Var< String > implements DataModel < LabelModel > {
    
    public LabelModel() {
    }
    
    public LabelModel( String label ) {
    	this.setValue( label );
    }
    
    public void fill( LabelModel model ) {
    	super.fill( model );
    }
    
}
