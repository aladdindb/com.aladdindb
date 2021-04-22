package com.xelara.aladdin.req.get.byid;

import com.xelara.aladdin.req.ReqModel;
import com.xelara.core.util.Var;


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
