package com.aladdindb.store.models;

import java.time.ZonedDateTime;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

public class TimeStamp implements DataModel<TimeStamp> {

	public final Var< ZonedDateTime > generatedOn 	= new Var<>();
	public final Var< ZonedDateTime > modifiedOn 	= new Var<>();
	 
	@Override
	public void fill( TimeStamp model ) {
		
		this.generatedOn.set( model.generatedOn );
		this.modifiedOn	.set( model.modifiedOn );

	}

}
