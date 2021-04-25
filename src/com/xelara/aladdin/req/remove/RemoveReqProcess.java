package com.xelara.aladdin.req.remove;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.resp.remove.RemoveResp;
import com.xelara.aladdin.resp.remove.RemoveRespParser;
import com.xelara.structure.DataModel;


public class RemoveReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < RemoveReqModel, RemoveResp< UDM > , UDM > {

	
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
