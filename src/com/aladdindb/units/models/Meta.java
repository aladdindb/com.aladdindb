package com.aladdindb.units.models;

import com.aladdindb.models.text.LabelModel;
import com.aladdindb.structure.Store;

/**
 *
 * @author Macit Kandemir
 */
public class Meta implements Store< Meta > { 
    
	
	public final LabelModel label 		= new LabelModel();
	public final TimeStamp 	timeStamp	= new TimeStamp();

	
	@Override
	public void fill( Meta  model ) {
		this.label		.fill( model.label );
		this.timeStamp	.fill( model.timeStamp );
	}

    
    
}
