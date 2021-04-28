package com.aladdindb.req.get.all;

import com.aladdindb.UnitsChannel;
import com.aladdindb.req.ReqProcess;
import com.aladdindb.resp.get.block.GetBlockRespModel;
import com.aladdindb.resp.get.block.GetBlockRespParser;
import com.aladdindb.structure.DataModel;


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
