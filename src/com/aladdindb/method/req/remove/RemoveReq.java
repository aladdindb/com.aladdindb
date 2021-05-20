package com.aladdindb.method.req.remove;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.Var;


public class RemoveReq extends Req< RemoveReq > {

	
	public final Var< String > unitID = new Var<>();

	
	public RemoveReq( String storeId, String unitID ) {
		super( storeId );
		this.unitID.set(unitID);
	}

	@Override
	public void fill( RemoveReq model ) {
		this.unitID.set( model.unitID );
	}
	

}
