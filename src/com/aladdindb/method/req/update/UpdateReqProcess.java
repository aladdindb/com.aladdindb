package com.aladdindb.method.req.update;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.update.UpdateResp;
import com.aladdindb.method.resp.update.UpdateRespTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;


public class UpdateReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < UpdateReq< UDM >, UpdateResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public UpdateReqProcess( Unit<UDM> unitData, MagicLamp< UDM > unitsChanel ) {
		
		var req = new UpdateReq < UDM > ( unitsChanel.unitGroupID, unitData );
		
		this.magicLamp.set ( unitsChanel);
		
		this.req		.set ( req );
		this.respTransformer	.set ( new UpdateRespTransformer() );
		this.reqTransformer	.set ( new UpdateReqTransformer< UDM > ( unitsChanel.unitDataTransformer ) );
	}

	

}
