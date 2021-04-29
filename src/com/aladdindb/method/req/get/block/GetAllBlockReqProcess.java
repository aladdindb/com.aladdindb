package com.aladdindb.method.req.get.block;

import com.aladdindb.UnitsChannel;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.GetBlockRespModel;
import com.aladdindb.method.resp.get.block.GetBlockRespParser;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.LineNavigator;


public class GetAllBlockReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < GetAllBlockReqModel, GetBlockRespModel, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetAllBlockReqProcess( String cmdSessionID, LineNavigator.DIRECTION direction, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetAllBlockReqModel( unitsChanel.unitGroupID, cmdSessionID, direction );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetAllBlockReqParser() );
		this.respParser	.set ( new GetBlockRespParser() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
