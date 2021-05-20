package com.aladdindb.method.req.close.method.session;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.Var;


public class CloseMethodSessionReq extends Req< CloseMethodSessionReq > {

	
	public final Var < String > methodSessionId	= new Var<>();

	
	public CloseMethodSessionReq( String storeId, String methodSessionId ) {
		super( storeId );
		
		this.methodSessionId 	.set( methodSessionId	);
	}

	@Override
	public void fill( CloseMethodSessionReq model ) {
		super.fill( model );
		
		this.methodSessionId	.set( model.methodSessionId 	);
	}

}
