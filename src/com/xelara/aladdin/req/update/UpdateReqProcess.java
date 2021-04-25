package com.xelara.aladdin.req.update;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.resp.update.UpdateResp;
import com.xelara.aladdin.resp.update.UpdateRespParser;
import com.xelara.structure.DataModel;


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
