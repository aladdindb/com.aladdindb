package com.xelara.aladdin.req.get.filtered;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.req.ReqProcess2;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.structure.DataModel;


public class GetFilteredReqProcess <

	UDM 			extends DataModel	< UDM >,
	FILTER_MODEL 	extends Filter		< UDM, FILTER_MODEL >

> extends ReqProcess2 < GetFilteredReqModel < UDM, FILTER_MODEL >, GetAllBlockResp , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetFilteredReqProcess( int blockSize, FILTER_MODEL filter,  UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetFilteredReqModel < UDM, FILTER_MODEL >( unitsChanel.unitGroupID, blockSize, filter );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetFilteredReqParser	< UDM, FILTER_MODEL >( unitsChanel.filterFac ) ); 
		this.respParser	.set ( new GetAllBlockRespParser() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
