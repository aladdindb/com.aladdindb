package com.aladdindb.method.req.get.block;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockResp;
import com.aladdindb.method.resp.get.block.BlockRespTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.LineNavigator;


public class BlockReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < BlockReq, BlockResp, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public BlockReqProcess( String cmdSessionID, LineNavigator.DIRECTION direction, MagicLamp< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new BlockReq( unitsChanel.unitGroupID, cmdSessionID, direction );
		
		this.req		.set ( req );
		this.reqParser	.set ( new BlockReqTransformer() );
		this.respParser	.set ( new BlockRespTransformer() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
