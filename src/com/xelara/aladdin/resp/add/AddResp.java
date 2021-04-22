package com.xelara.aladdin.resp.add;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;


public class AddResp  implements DataModel< AddResp > {

	
	public final Var<String> unitID	= new Var<>();
	
	
	public AddResp() {
	}
	
	public AddResp( String unitID ) {
		this.unitID.set( unitID );
	}
	
	@Override
	public void fill( AddResp model ) {
		this.unitID.set( model.unitID );
	}

}
