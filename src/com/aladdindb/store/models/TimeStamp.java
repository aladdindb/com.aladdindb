package com.aladdindb.store.models;

import java.time.ZonedDateTime;

import com.aladdindb.structure.DefaultDataModel;
import com.aladdindb.util.Parent;
import com.aladdindb.util.Var;

public class TimeStamp extends DefaultDataModel<TimeStamp> {

	public final Var< ZonedDateTime > generatedOn 	= new Var<>( null, this );
	public final Var< ZonedDateTime > modifiedOn 	= new Var<>( null, this );
	 
	public TimeStamp( Parent parent ) {
		super ( parent );
	}
	
	@Override
	public void fill( TimeStamp model ) {
		
		this.generatedOn.set( model.generatedOn );
		this.modifiedOn	.set( model.modifiedOn );

	}

}
