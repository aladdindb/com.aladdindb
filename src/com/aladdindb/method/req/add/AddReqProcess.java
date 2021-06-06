package com.aladdindb.method.req.add;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.add.AddResp;
import com.aladdindb.method.resp.add.AddRespTransformer;
import com.aladdindb.structure.DataModel;


public class AddReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < AddReq< UDM >, AddResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public AddReqProcess( UDM unitData, MagicLamp< UDM > magicLamp ) {
		
		this.magicLamp.set ( magicLamp);

		var req = new AddReq < UDM > ( magicLamp.support.getStoreId(), unitData );
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new AddReqTransformer	< UDM > ( magicLamp.support.udmClass() ) );
		this.respTransformer	.set ( new AddRespTransformer() );
	}

	

}
