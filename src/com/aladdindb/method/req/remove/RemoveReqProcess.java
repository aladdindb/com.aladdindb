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

	public RemoveReqProcess( String unitID, MagicLamp< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new RemoveReq( unitsChanel.unitGroupID, unitID );
		
		this.req		.set ( req );
		this.reqParser	.set ( new RemoveReqTransformer());
		this.respParser	.set ( new RemoveRespTransformer<UDM>( unitsChanel.unitDataTransformer ) );
	}

	

}
