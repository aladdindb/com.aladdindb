package com.xelara.aladdin.req.get.by.filter;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.resp.get.block.GetBlockRespModel;
import com.xelara.aladdin.resp.get.block.GetBlockRespParser;
import com.xelara.structure.DataModel;


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
