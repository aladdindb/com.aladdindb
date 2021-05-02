package com.aladdindb.method.req.get.all;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockResp;
import com.aladdindb.method.resp.get.block.BlockRespTransformer;
import com.aladdindb.structure.DataModel;


public class GetAllReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < GetAllReq, BlockResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetAllReqProcess( int blockSize,  MagicLamp< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetAllReq( unitsChanel.unitGroupID, blockSize );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetAllReqTransformer() );
		this.respParser	.set ( new BlockRespTransformer() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
