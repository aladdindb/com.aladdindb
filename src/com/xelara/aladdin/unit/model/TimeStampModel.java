package com.xelara.aladdin.unit.model;

import com.xelara.core.Var;

public class TimeStampModel implements DataModel<TimeStampModel> {

	public final Var< String > create = new Var<String>();
	public final Var< String > update = new Var<String>();
	
	@Override
	public void fill( TimeStampModel model ) {
		
		this.create.fill( model.create );
		this.update.fill( model.update );

	}

}
