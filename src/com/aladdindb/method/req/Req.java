package com.aladdindb.method.req;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

public abstract class Req < DATA_MODEL extends Req< DATA_MODEL > > implements DataModel< DATA_MODEL > { 


	public final Var< String > storeId = new Var<>();
	
	
	public Req( String storeId ) {
		
		this.storeId.set( storeId );
	}

	@Override
	public void fill( DATA_MODEL  model ) { 
		this.storeId.set( model.storeId );
	}
	
}
