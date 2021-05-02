package com.aladdindb.method.req.get.by.id;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.by.id.GetByIdResp;
import com.aladdindb.method.resp.get.by.id.GetByIdRespTransformer;
import com.aladdindb.structure.DataModel;


public class GetByIdReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < GetByIdReq, GetByIdResp < UDM > , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetByIdReqProcess( String unitID, MagicLamp< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetByIdReq( unitsChanel.unitGroupID, unitID );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetByIdReqTransformer());
		this.respParser	.set ( new GetByIdRespTransformer < UDM > ( unitsChanel.unitDataTransformer ) );
	}

	

}
