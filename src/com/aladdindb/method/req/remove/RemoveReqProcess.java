package com.aladdindb.method.req.remove;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.remove.RemoveResp;
import com.aladdindb.method.resp.remove.RemoveRespTransformer;
import com.aladdindb.structure.DataModel;


public class RemoveReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < RemoveReq, RemoveResp< UDM > , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public RemoveReqProcess( String unitID, MagicLamp< UDM > magicLamp ) {
		
		this.magicLamp.set ( magicLamp);

		var req = new RemoveReq( magicLamp.storeId, unitID );
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new RemoveReqTransformer());
		this.respTransformer	.set ( new RemoveRespTransformer<UDM>( magicLamp.udmClass ) );
	}

	

}
