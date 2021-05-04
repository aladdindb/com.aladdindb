package com.aladdindb.method.req.closemethodsession;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.closemethodsession.CloseMethodSessionResp;
import com.aladdindb.method.resp.closemethodsession.CloseMethodSessionRespTransformer;
import com.aladdindb.structure.DataModel;


public class CloseMethodSessionReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < CloseMethodSessionReq, CloseMethodSessionResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public CloseMethodSessionReqProcess( String methodSessionID, MagicLamp< UDM > magicLamp ) {
		
		this.magicLamp.set ( magicLamp);

		var req = new CloseMethodSessionReq( magicLamp.unitGroupID, methodSessionID ); 
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new CloseMethodSessionReqTransformer	() );
		this.respTransformer	.set ( new CloseMethodSessionRespTransformer() );
	}

	

}
