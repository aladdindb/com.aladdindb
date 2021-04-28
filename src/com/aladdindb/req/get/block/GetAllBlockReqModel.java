package com.aladdindb.req.get.block;

import com.aladdindb.req.ReqModel;
import com.aladdindb.util.LineNavigator;
import com.aladdindb.util.Var;


public class GetAllBlockReqModel extends ReqModel< GetAllBlockReqModel > {

	
	public final Var < String > 	cmdSessionID 	= new Var<>();
	public final Var < LineNavigator.DIRECTION > 	direction 		= new Var<>();

	
	public GetAllBlockReqModel( String unitGroupID, String cmdSessionID, LineNavigator.DIRECTION direction ) {
		super( unitGroupID );
		this.cmdSessionID 	.set( cmdSessionID	);
		this.direction		.set( direction		);
	}

	@Override
	public void fill( GetAllBlockReqModel model ) {
		super.fill( model );
		
		this.cmdSessionID	.set( model.cmdSessionID 	);
		this.direction		.set( model.direction 		);
	}

}
