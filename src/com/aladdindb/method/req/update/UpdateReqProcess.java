package com.aladdindb.method.req.update;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.update.UpdateResp;
import com.aladdindb.method.resp.update.UpdateRespTransformer;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;


public class UpdateReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < UpdateReq< UDM >, UpdateResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public UpdateReqProcess( Unit<UDM> unitData, MagicLamp< UDM > magicLamp ) {
		
		var req = new UpdateReq < UDM > ( magicLamp.support.storeId, unitData );
		
		this.magicLamp.set ( magicLamp);
		
		this.req		.set ( req );
		this.respTransformer	.set ( new UpdateRespTransformer() );
		this.reqTransformer	.set ( new UpdateReqTransformer< UDM > ( magicLamp.support.udmClass ) );
	}

	

}
