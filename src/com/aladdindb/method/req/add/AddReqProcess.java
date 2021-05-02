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

	public AddReqProcess( UDM unitData, MagicLamp< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new AddReq < UDM > ( unitsChanel.unitGroupID, unitData );
		
		this.req		.set ( req );
		this.reqParser	.set ( new AddReqTransformer	< UDM > ( unitsChanel.unitDataTransformer ) );
		this.respParser	.set ( new AddRespTransformer() );
	}

	

}
