package com.xelara.aladdin.req.get.by.id;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.resp.get.by.id.GetByIdRespModel;
import com.xelara.aladdin.resp.get.by.id.GetByIdRespParser;
import com.xelara.structure.DataModel;


public class GetByIdReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < GetByIdReqModel, GetByIdRespModel < UDM > , UDM > {

	
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
