package com.xelara.aladdin.req.update;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.add.AddResp;
import com.xelara.aladdin.resp.add.AddRespParser;
import com.xelara.aladdin.resp.update.UpdateResp;
import com.xelara.aladdin.resp.update.UpdateRespParser;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;


public class UpdateReqProcess < UDM extends DataModel< UDM > > extends ReqProcess2 < UpdateReqModel< UDM >, UpdateResp, UDM > {

	
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
