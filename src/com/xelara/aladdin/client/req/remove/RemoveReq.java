package com.xelara.aladdin.client.req.remove;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class RemoveReq implements DataModel< RemoveReq>  {

	public final Var< String > 		unitGroupID = new Var<>();
	public final Var< String > 		unitID 		= new Var<>();
	
	public RemoveReq() {
	}
	
	public RemoveReq( String unitGroupID,  String unitID ) {
		this.unitGroupID 	.set( unitGroupID );
		this.unitID			.set( unitID );
	}
	
	@Override
	public void fill( RemoveReq model ) {
		this.unitGroupID	.set( model.unitGroupID );
		this.unitID			.set( model.unitID );
	}

}
