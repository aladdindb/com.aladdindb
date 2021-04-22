package com.xelara.aladdin.req.remove;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.add.AddResp;
import com.xelara.aladdin.resp.add.AddRespParser;
import com.xelara.aladdin.resp.remove.RemoveResp;
import com.xelara.aladdin.resp.remove.RemoveRespParser;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;


public class RemoveReqProcess < UDM extends DataModel< UDM > > extends ReqProcess2 < RemoveReqModel, RemoveResp< UDM > , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public RemoveReqProcess( String unitID, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new RemoveReqModel( unitsChanel.unitGroupID, unitID );
		
		this.req		.set ( req );
		this.reqParser	.set ( new RemoveReqParser());
		this.respParser	.set ( new RemoveRespParser<UDM>( unitsChanel.unitDataParser ) );
	}

	

}
