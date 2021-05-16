package com.aladdindb.method.resp.update;

import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;

public class UpdateResp  implements Store< UpdateResp > {

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
