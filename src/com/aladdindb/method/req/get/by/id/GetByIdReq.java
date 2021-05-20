package com.aladdindb.method.req.get.by.id;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.Var;


public class GetByIdReq extends Req< GetByIdReq > {

	
	public final Var< String > unitID = new Var<>();

	
	public GetByIdReq( String storeId, String unitID ) {
		super( storeId );
		this.unitID.set(unitID);
	}

	@Override
	public void fill( GetByIdReq model ) {
		this.unitID.set( model.unitID );
	}
	

}
