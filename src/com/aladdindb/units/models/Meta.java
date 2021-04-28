package com.aladdindb.units.models;

import com.aladdindb.defaultmodels.text.LabelModel;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class Meta implements DataModel< Meta > { 
    
	
	public final LabelModel label 		= new LabelModel();
	public final TimeStamp 	timeStamp	= new TimeStamp();

	
	@Override
	public void fill( Meta  model ) {
		this.label		.fill( model.label );
		this.timeStamp	.fill( model.timeStamp );
	}

    
    
}
