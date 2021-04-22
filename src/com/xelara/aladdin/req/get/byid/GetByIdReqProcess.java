package com.xelara.aladdin.req.get.byid;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.add.AddResp;
import com.xelara.aladdin.resp.add.AddRespParser;
import com.xelara.aladdin.resp.get.byid.GetByIdResp;
import com.xelara.aladdin.resp.get.byid.GetByIdRespParser;
import com.xelara.aladdin.resp.remove.RemoveResp;
import com.xelara.aladdin.resp.remove.RemoveRespParser;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;


public class GetByIdReqProcess < UDM extends DataModel< UDM > > extends ReqProcess2 < GetByIdReqModel, GetByIdResp < UDM > , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetByIdReqProcess( String unitID, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetByIdReqModel( unitsChanel.unitGroupID, unitID );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetByIdReqParser());
		this.respParser	.set ( new GetByIdRespParser < UDM > ( unitsChanel.unitDataParser ) );
	}

	

}
