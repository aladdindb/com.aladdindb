package com.aladdindb.resp.add;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class AddRespModel  implements DataModel< AddRespModel > {

	
	public final Var<String> unitID	= new Var<>();
	
	
	public AddRespModel() {
	}
	
	public AddRespModel( String unitID ) {
		this.unitID.set( unitID );
	}
	
	@Override
	public void fill( AddRespModel model ) {
		this.unitID.set( model.unitID );
	}

}
