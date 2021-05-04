package com.aladdindb.method.req.close.method.session;

import com.aladdindb.method.req.Req;
import com.aladdindb.util.Var;


public class CloseMethodSessionReq extends Req< CloseMethodSessionReq > {

	
	public final Var < String > methodSessionID	= new Var<>();

	
	public CloseMethodSessionReq( String unitGroupID, String methodSessionID ) {
		super( unitGroupID );
		
		this.methodSessionID 	.set( methodSessionID	);
	}

	@Override
	public void fill( CloseMethodSessionReq model ) {
		super.fill( model );
		
		this.methodSessionID	.set( model.methodSessionID 	);
	}

}
