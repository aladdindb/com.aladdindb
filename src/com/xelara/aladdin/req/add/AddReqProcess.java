package com.xelara.aladdin.req.add;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.add.AddResp;
import com.xelara.aladdin.resp.add.AddRespParser;
import com.xelara.structure.DataModel;


public class AddReqProcess < UDM extends DataModel< UDM > > extends ReqProcess2 < AddReqModel< UDM >, AddResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public AddReqProcess( UDM unitData, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new AddReqModel < UDM > ( unitsChanel.unitGroupID, unitData );
		
		this.req		.set ( req );
		this.reqParser	.set ( new AddReqParser	< UDM > ( unitsChanel.unitDataParser ) );
		this.respParser	.set ( new AddRespParser() );
	}

	

}
