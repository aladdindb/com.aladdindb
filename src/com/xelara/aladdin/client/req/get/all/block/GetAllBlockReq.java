package com.xelara.aladdin.client.req.get.all.block;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class GetAllBlockReq implements DataModel< GetAllBlockReq>  {

	public final Var < String > 	unitGroupID 	= new Var<>();
	public final Var < String > 	cmdSessionID 	= new Var<>();
	public final Var < String > 	direction 		= new Var<>();
	
	public GetAllBlockReq() {
	}
	
	public GetAllBlockReq( String unitGroupID, String cmdSessionID,  String direction ) {
		this.unitGroupID 	.set( unitGroupID	);
		this.cmdSessionID 	.set( cmdSessionID	);
		this.direction		.set( direction		);
	}
	
	@Override
	public void fill( GetAllBlockReq model ) {
		this.unitGroupID	.set( model.unitGroupID 	);
		this.cmdSessionID	.set( model.cmdSessionID 	);
		this.direction		.set( model.direction 		);
	}

}
