package com.aladdindb.method.req.get.block;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.LineNavigator;
import com.aladdindb.util.Var;


public class BlockReq extends Req< BlockReq > {

	
	public final Var < String > 	cmdSessionID 	= new Var<>();
	public final Var < LineNavigator.DIRECTION > 	direction 		= new Var<>();

	
	public BlockReq( String unitGroupID, String cmdSessionID, LineNavigator.DIRECTION direction ) {
		super( unitGroupID );
		this.cmdSessionID 	.set( cmdSessionID	);
		this.direction		.set( direction		);
	}

	@Override
	public void fill( BlockReq model ) {
		super.fill( model );
		
		this.cmdSessionID	.set( model.cmdSessionID 	);
		this.direction		.set( model.direction 		);
	}

}
