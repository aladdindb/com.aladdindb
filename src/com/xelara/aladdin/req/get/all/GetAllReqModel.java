package com.xelara.aladdin.req.get.all;

import com.xelara.aladdin.req.ReqModel;
import com.xelara.core.util.LineNavigator;
import com.xelara.core.util.Var;


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
