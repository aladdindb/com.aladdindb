package com.aladdindb.method.req.get.block;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.structure.Store;
import com.aladdindb.util.LineNavigator;


public class BlockNaviReqProcess < UDM extends Store< UDM > > extends ReqProcess < BlockNaviReq, BlockNavResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public BlockNaviReqProcess( String cmdSessionID, LineNavigator.DIRECTION direction, MagicLamp< UDM > unitsChanel ) {
		
		this.magicLamp.set ( unitsChanel);

		var req = new BlockNaviReq( unitsChanel.unitGroupID, cmdSessionID, direction );
		
		this.req		.set ( req );
		this.reqTransformer	.set ( new BlockNaviReqTransformer() );
		this.respTransformer	.set ( new BlockNaviRespTransformer() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
