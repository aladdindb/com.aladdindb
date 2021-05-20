package com.aladdindb.method.req.get.block;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.LineNavigator;
import com.aladdindb.util.Var;


public class BlockNaviReq extends Req< BlockNaviReq > {

	
	public final Var < String > 					methodSessionID 	= new Var<>();
	public final Var < LineNavigator.DIRECTION > 	direction 			= new Var<>();

	
	public BlockNaviReq( String storeId, String methodSessionID, LineNavigator.DIRECTION direction ) {
		super( storeId );
		this.methodSessionID 	.set( methodSessionID	);
		this.direction			.set( direction		);
	}

	@Override
	public void fill( BlockNaviReq model ) {
		super.fill( model );
		
		this.methodSessionID	.set( model.methodSessionID 	);
		this.direction			.set( model.direction 		);
	}

}
