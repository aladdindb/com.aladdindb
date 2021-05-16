package com.aladdindb.method.resp.add;

import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;


public class AddResp  implements Store< AddResp > {

	
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
