package com.aladdindb.method.req;

import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;

public abstract class Req < DATA_MODEL extends Req< DATA_MODEL > > implements Store< DATA_MODEL > { 


	public final Var< String > unitGroupID = new Var<>();
	
	
	public Req( String unitGroupID ) {
		
		this.unitGroupID.set( unitGroupID );
	}

	@Override
	public void fill( DATA_MODEL  model ) { 
		this.unitGroupID.set( model.unitGroupID );
	}
	
}
