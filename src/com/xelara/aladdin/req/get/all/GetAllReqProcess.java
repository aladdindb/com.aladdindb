package com.xelara.aladdin.req.get.all;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.add.AddResp;
import com.xelara.aladdin.resp.add.AddRespParser;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.aladdin.resp.get.byid.GetByIdResp;
import com.xelara.aladdin.resp.get.byid.GetByIdRespParser;
import com.xelara.aladdin.resp.remove.RemoveResp;
import com.xelara.aladdin.resp.remove.RemoveRespParser;
import com.xelara.core.util.LineNavigator;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;


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
