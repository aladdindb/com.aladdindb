package com.xelara.aladdin.resp.get.block;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;


public class GetBlockRespModel  implements DataModel< GetBlockRespModel  > {

	
	public final Var<String> 	cmdSessionID 	= new Var<>();
	public final Var<Boolean> 	hasLeft 		= new Var<>();
	public final Var<Boolean> 	hasRight 		= new Var<>();

	public final Var<String> 	unitsIdBlock 	= new Var<>();
	
	@Override
	public void fill( GetBlockRespModel model ) {
		this.cmdSessionID	.set( model.cmdSessionID );
		
		this.hasLeft		.set( model.hasLeft );
		this.hasRight		.set( model.hasRight );
		
		this.unitsIdBlock	.set( model.unitsIdBlock );
	}
	

}
