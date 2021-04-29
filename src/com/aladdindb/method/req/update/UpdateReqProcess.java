package com.aladdindb.method.req.update;

import com.aladdindb.UnitsChannel;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.update.UpdateResp;
import com.aladdindb.method.resp.update.UpdateRespParser;
import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;


public class UpdateReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < UpdateReqModel< UDM >, UpdateResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public UpdateReqProcess( Unit<UDM> unitData, UnitsChannel< UDM > unitsChanel ) {
		
		var req = new UpdateReqModel < UDM > ( unitsChanel.unitGroupID, unitData );
		
		this.unitsChanel.set ( unitsChanel);
		
		this.req		.set ( req );
		this.respParser	.set ( new UpdateRespParser() );
		this.reqParser	.set ( new UpdateReqParser< UDM > ( unitsChanel.unitDataParser ) );
	}

	

}
