package com.xelara.aladdin.req.get.all.block;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.core.util.LineNavigator;
import com.xelara.structure.DataModel;


public class GetAllBlockReqProcess < UDM extends DataModel< UDM > > extends ReqProcess2 < GetAllBlockReqModel, GetAllBlockResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetAllBlockReqProcess( String cmdSessionID, LineNavigator.DIRECTION direction, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetAllBlockReqModel( unitsChanel.unitGroupID, cmdSessionID, direction );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetAllBlockReqParser() );
		this.respParser	.set ( new GetAllBlockRespParser() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
