package com.xelara.aladdin.req.remove;

import com.xelara.aladdin.req.ReqModel;
import com.xelara.core.util.Var;


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
