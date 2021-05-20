package com.aladdindb.method.req.get.by.id;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.Var;


public class GetByIdReq extends Req< GetByIdReq > {

	
	public final Var< String > unitId = new Var<>();

	
	public GetByIdReq( String storeId, String unitId ) {
		super( storeId );
		this.unitId.set(unitId);
	}

	@Override
	public void fill( GetByIdReq model ) {
		this.unitId.set( model.unitId );
	}
	

}
