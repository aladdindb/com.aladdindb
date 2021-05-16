package com.aladdindb.method.req.add;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.add.AddResp;
import com.aladdindb.method.resp.add.AddRespTransformer;
import com.aladdindb.structure.Store;


public class AddReqProcess < UDM extends Store< UDM > > extends ReqProcess < AddReq< UDM >, AddResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public AddReqProcess( UDM unitData, MagicLamp< UDM > unitsChanel ) {
		
		this.magicLamp.set ( unitsChanel);

		var req = new AddReq < UDM > ( unitsChanel.unitGroupID, unitData );
		
		this.req		.set ( req );
		this.reqTransformer	.set ( new AddReqTransformer	< UDM > ( unitsChanel.unitDataTransformer ) );
		this.respTransformer	.set ( new AddRespTransformer() );
	}

	

}
