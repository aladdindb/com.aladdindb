package com.aladdindb.method.req.get.by.finder;

import com.aladdindb.MagicLamp;
import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.structure.DataModel;


public class GetByFinderReqProcess <

	UDM 			extends DataModel	< UDM >,
	FILTER_MODEL 	extends Finder		< UDM, FILTER_MODEL >

> extends ReqProcess < GetByFinderReq < UDM, FILTER_MODEL >, BlockNavResp , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetByFinderReqProcess( int blockSize, FILTER_MODEL filter,  MagicLamp< UDM > unitsChanel ) {
		
		this.magicLamp.set ( unitsChanel);

		var req = new GetByFinderReq < UDM, FILTER_MODEL >( unitsChanel.unitGroupID, blockSize, filter );
		
		this.req		.set ( req );
		this.reqTransformer	.set ( new GetByFinderReqTransformer	< UDM, FILTER_MODEL >( unitsChanel.finderSupplier ) ); 
		this.respTransformer	.set ( new BlockNaviRespTransformer() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
