package com.xelara.aladdin.resp.add;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;


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
