package com.aladdindb.store.models;

import com.aladdindb.models.text.UnitLabel;
import com.aladdindb.structure.DataModel;

/**
 *
 * @author Macit Kandemir
 */
public class Meta implements DataModel< Meta > { 
    
	
	public final UnitLabel label 		= new UnitLabel();
	public final TimeStamp 	timeStamp	= new TimeStamp();

	
	@Override
	public void fill( Meta  model ) {
		this.label		.fill( model.label );
		this.timeStamp	.fill( model.timeStamp );
	}

    
    
}
