package com.aladdindb.store.models;

import com.aladdindb.models.text.UnitLabel;
import com.aladdindb.structure.DefaultDataModel;
import com.aladdindb.util.Parent;

/**
 *
 * @author Macit Kandemir
 */
public class Meta extends  DefaultDataModel< Meta > { 
    
	
	public final UnitLabel 	label 		= new UnitLabel( this );
	public final TimeStamp 	timeStamp	= new TimeStamp( this );

	public Meta( Parent parent ) {
		super( parent );
	}
	
	@Override
	public void fill( Meta  model ) {
		this.label		.fill( model.label );
		this.timeStamp	.fill( model.timeStamp );
	}

    
    
}
