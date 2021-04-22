package com.xelara.aladdin.req;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public abstract class ReqModel < DATA_MODEL extends ReqModel< DATA_MODEL > > implements DataModel< DATA_MODEL > { 


	public final Var< String > unitGroupID = new Var<>();
	
	
	public ReqModel( String unitGroupID ) {
		
		this.unitGroupID.set( unitGroupID );
	}

	@Override
	public void fill( DATA_MODEL  model ) { 
		this.unitGroupID.set( model.unitGroupID );
	}
	
}
