package com.xelara.aladdin.client.req.get.byid;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class GetByIdReq implements DataModel< GetByIdReq>  {

	public final Var< String > 		unitGroupID = new Var<>();
	public final Var< String > 		unitID 		= new Var<>();
	
	public GetByIdReq() {
	}
	
	public GetByIdReq( String unitGroupID,  String unitID ) {
		this.unitGroupID 	.set(unitGroupID);
		this.unitID			.set(unitID);
	}
	
	@Override
	public void fill( GetByIdReq model ) {
		this.unitGroupID	.set( model.unitGroupID );
		this.unitID			.set( model.unitID );
	}

}
