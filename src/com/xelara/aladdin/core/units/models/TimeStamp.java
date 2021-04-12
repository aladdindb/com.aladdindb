package com.xelara.aladdin.core.units.models;

import java.time.ZonedDateTime;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class TimeStamp implements DataModel<TimeStamp> {

	public final Var< ZonedDateTime > create = new Var<>();
	public final Var< ZonedDateTime > update = new Var<>();
	
	@Override
	public void fill( TimeStamp model ) {
		
		this.create.set( model.create );
		this.update.set( model.update );

	}

}
