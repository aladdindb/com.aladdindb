package com.xelara.aladdin.resp.get.all.block;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.core.util.Var;


public class GetAllBlockResp  implements DataModel< GetAllBlockResp  > {

	
	public final Var<String> 	cmdSessionID 	= new Var<>();
	public final Var<Boolean> 	hasLeft 		= new Var<>();
	public final Var<Boolean> 	hasRight 		= new Var<>();

	public final Var<String> 	unitsIdBlock 	= new Var<>();
	
	@Override
	public void fill( GetAllBlockResp model ) {
		this.cmdSessionID	.set( model.cmdSessionID );
		
		this.hasLeft		.set( model.hasLeft );
		this.hasRight		.set( model.hasRight );
		
		this.unitsIdBlock	.set( model.unitsIdBlock );
	}
	

}
