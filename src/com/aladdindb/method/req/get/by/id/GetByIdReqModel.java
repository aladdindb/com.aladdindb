package com.aladdindb.method.req.get.by.id;

import com.aladdindb.method.req.ReqModel;
import com.aladdindb.util.Var;


public class GetByIdReqModel extends ReqModel< GetByIdReqModel > {

	
	public final Var< String > unitID = new Var<>();

	
	public GetByIdReqModel( String unitGroupID, String unitID ) {
		super( unitGroupID );
		this.unitID.set(unitID);
	}

	@Override
	public void fill( GetByIdReqModel model ) {
		this.unitID.set( model.unitID );
	}
	

}
