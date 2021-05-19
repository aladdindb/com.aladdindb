package com.aladdindb.method.req.get.block;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.LineNavigator;


public class BlockNaviReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < BlockNaviReq, BlockNavResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public BlockNaviReqProcess( String cmdSessionID, LineNavigator.DIRECTION direction, MagicLamp< UDM > magicLamp ) {
		
		this.magicLamp.set ( magicLamp);

		var req = new BlockNaviReq( magicLamp.support.getStoreId(), cmdSessionID, direction );
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new BlockNaviReqTransformer() );
		this.respTransformer	.set ( new BlockNaviRespTransformer() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
