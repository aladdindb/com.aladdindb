package com.xelara.aladdin.req.get.all;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.resp.get.block.GetBlockRespModel;
import com.xelara.aladdin.resp.get.block.GetBlockRespParser;
import com.xelara.structure.DataModel;


public class GetAllReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < GetAllReqModel, GetBlockRespModel, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetAllReqProcess( int blockSize,  UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetAllReqModel( unitsChanel.unitGroupID, blockSize );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetAllReqParser() );
		this.respParser	.set ( new GetBlockRespParser() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
