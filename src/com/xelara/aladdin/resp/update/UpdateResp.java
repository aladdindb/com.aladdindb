package com.xelara.aladdin.resp.update;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;

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
