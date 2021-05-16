package com.aladdindb.method.req.get.by.id;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.by.id.GetByIdResp;
import com.aladdindb.method.resp.get.by.id.GetByIdRespTransformer;
import com.aladdindb.structure.Store;


public class GetByIdReqProcess < UDM extends Store< UDM > > extends ReqProcess < GetByIdReq, GetByIdResp < UDM > , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetByIdReqProcess( String unitID, MagicLamp< UDM > unitsChanel ) {
		
		this.magicLamp.set ( unitsChanel);

		var req = new GetByIdReq( unitsChanel.unitGroupID, unitID );
		
		this.req		.set ( req );
		this.reqTransformer	.set ( new GetByIdReqTransformer());
		this.respTransformer	.set ( new GetByIdRespTransformer < UDM > ( unitsChanel.unitDataTransformer ) );
	}

	

}
