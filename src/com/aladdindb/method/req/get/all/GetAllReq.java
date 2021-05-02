package com.aladdindb.method.req.get.all;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.LineNavigator;
import com.aladdindb.util.Var;


public class GetAllReq extends Req< GetAllReq > {

	
	public final Var < Integer > blockSize 	= new Var<>();

	
	public GetAllReq( String unitGroupID, int blockSize ) {
		super( unitGroupID );
		
		this.blockSize.set( blockSize );
	}

	@Override
	public void fill( GetAllReq model ) {
		super.fill( model );
		
		this.blockSize.set( model.blockSize );
	}

}
