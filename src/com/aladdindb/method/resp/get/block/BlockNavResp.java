package com.aladdindb.method.resp.get.block;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class BlockNavResp  implements DataModel< BlockNavResp  > {

	
	public final Var<String> 	methodSessionId 	= new Var<>();
	public final Var<Boolean> 	hasLeft 			= new Var<>();
	public final Var<Boolean> 	hasRight 			= new Var<>();

	public final Var<String> 	unitsIdBlock 		= new Var<>();
	
	@Override
	public void fill( BlockNavResp model ) {
		this.methodSessionId	.set( model.methodSessionId );
		
		this.hasLeft			.set( model.hasLeft );
		this.hasRight			.set( model.hasRight );
		
		this.unitsIdBlock		.set( model.unitsIdBlock );
	}
	

}
