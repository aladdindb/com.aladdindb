package com.xelara.aladdin.server.resp.update;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class UpdateResp  implements DataModel< UpdateResp > {

	public final Var<String> unitID	= new Var<>();
	
	public UpdateResp() {
	}
	
	public UpdateResp( String unitID ) {
		this.unitID.set(unitID);
	}
	
	@Override
	public void fill( UpdateResp model ) {
		this.unitID.set( model.unitID );
	}

}
