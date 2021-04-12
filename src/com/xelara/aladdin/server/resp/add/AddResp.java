package com.xelara.aladdin.server.resp.add;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class AddResp  implements DataModel< AddResp > {

	public final Var<String> unitID	= new Var<>();
	
	public AddResp() {
	}
	
	public AddResp( String unitID ) {
		this.unitID.set(unitID);
	}
	
	@Override
	public void fill( AddResp model ) {
		this.unitID.set( model.unitID );
	}

}
