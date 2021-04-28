package com.aladdindb.req.get.all;

import com.aladdindb.req.ReqModel;
import com.aladdindb.util.LineNavigator;
import com.aladdindb.util.Var;


public class GetAllReqModel extends ReqModel< GetAllReqModel > {

	
	public final Var < Integer > blockSize 	= new Var<>();

	
	public GetAllReqModel( String unitGroupID, int blockSize ) {
		super( unitGroupID );
		
		this.blockSize.set( blockSize );
	}

	@Override
	public void fill( GetAllReqModel model ) {
		super.fill( model );
		
		this.blockSize.set( model.blockSize );
	}

}
