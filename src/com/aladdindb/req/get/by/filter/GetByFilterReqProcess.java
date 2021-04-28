package com.aladdindb.req.get.by.filter;

import com.aladdindb.UnitsChannel;
import com.aladdindb.filter.Filter;
import com.aladdindb.req.ReqProcess;
import com.aladdindb.resp.get.block.GetBlockRespModel;
import com.aladdindb.resp.get.block.GetBlockRespParser;
import com.aladdindb.structure.DataModel;


public class GetByFilterReqProcess <

	UDM 			extends DataModel	< UDM >,
	FILTER_MODEL 	extends Filter		< UDM, FILTER_MODEL >

> extends ReqProcess < GetByFilterReqModel < UDM, FILTER_MODEL >, GetBlockRespModel , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetByFilterReqProcess( int blockSize, FILTER_MODEL filter,  UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetByFilterReqModel < UDM, FILTER_MODEL >( unitsChanel.unitGroupID, blockSize, filter );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetByFilterReqParser	< UDM, FILTER_MODEL >( unitsChanel.filterFac ) ); 
		this.respParser	.set ( new GetBlockRespParser() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
