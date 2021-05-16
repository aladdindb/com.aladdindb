package com.aladdindb.method.req.get.all;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.structure.DataModel;


public class GetAllReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < GetAllReq, BlockNavResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetAllReqProcess( int blockSize,  MagicLamp< UDM > magicLamp ) {
		
		this.magicLamp.set ( magicLamp);

		var req = new GetAllReq( magicLamp.unitGroupID, blockSize );
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new GetAllReqTransformer() );
		this.respTransformer	.set ( new BlockNaviRespTransformer() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
