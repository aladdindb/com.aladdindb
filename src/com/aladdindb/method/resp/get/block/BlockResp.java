package com.aladdindb.method.resp.get.block;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class BlockResp  implements DataModel< BlockResp  > {

	
	public final Var<String> 	cmdSessionID 	= new Var<>();
	public final Var<Boolean> 	hasLeft 		= new Var<>();
	public final Var<Boolean> 	hasRight 		= new Var<>();

	public final Var<String> 	unitsIdBlock 	= new Var<>();
	
	@Override
	public void fill( BlockResp model ) {
		this.cmdSessionID	.set( model.cmdSessionID );
		
		this.hasLeft		.set( model.hasLeft );
		this.hasRight		.set( model.hasRight );
		
		this.unitsIdBlock	.set( model.unitsIdBlock );
	}
	

}
