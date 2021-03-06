package com.aladdindb.method.req.close.method.session;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.close.method.session.CloseMethodSessionResp;
import com.aladdindb.method.resp.close.method.session.CloseMethodSessionRespTransformer;
import com.aladdindb.structure.DataModel;


public class CloseMethodSessionReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < CloseMethodSessionReq, CloseMethodSessionResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public CloseMethodSessionReqProcess( String sessionId, MagicLamp< UDM > magicLamp ) {
		
		this.magicLamp.set ( magicLamp);

		var req = new CloseMethodSessionReq( magicLamp.storeId, sessionId ); 
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new CloseMethodSessionReqTransformer	() );
		this.respTransformer	.set ( new CloseMethodSessionRespTransformer() );
	}

	

}
