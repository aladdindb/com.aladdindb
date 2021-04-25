package com.xelara.aladdin.req.get.all;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.structure.DataModel;


public class GetAllReqProcess < UDM extends DataModel< UDM > > extends ReqProcess2 < GetAllReqModel, GetAllBlockResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetAllReqProcess( int blockSize,  UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetAllReqModel( unitsChanel.unitGroupID, blockSize );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetAllReqParser() );
		this.respParser	.set ( new GetAllBlockRespParser() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
