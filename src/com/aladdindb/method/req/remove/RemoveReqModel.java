package com.aladdindb.method.req.remove;

import com.aladdindb.method.req.ReqModel;
import com.aladdindb.util.Var;


public class RemoveReqModel extends ReqModel< RemoveReqModel > {

	
	public final Var< String > unitID = new Var<>();

	
	public RemoveReqModel( String unitGroupID, String unitID ) {
		super( unitGroupID );
		this.unitID.set(unitID);
	}

	@Override
	public void fill( RemoveReqModel model ) {
		this.unitID.set( model.unitID );
	}
	

}
