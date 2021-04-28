package com.aladdindb.resp.update;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

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
