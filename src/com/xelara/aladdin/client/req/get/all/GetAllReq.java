package com.xelara.aladdin.client.req.get.all;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class GetAllReq implements DataModel< GetAllReq>  {

	public final Var< String > 		unitGroupID = new Var<>();
	public final Var< Integer > 	blockSize 	= new Var<>();
	
	public GetAllReq() {
	}
	
	public GetAllReq( String unitGroupID,  Integer blockSize ) {
		this.unitGroupID 	.set(unitGroupID);
		this.blockSize		.set(blockSize);
	}
	
	@Override
	public void fill( GetAllReq model ) {
		this.unitGroupID	.set( model.unitGroupID );
		this.blockSize		.set( model.blockSize );
	}

}
