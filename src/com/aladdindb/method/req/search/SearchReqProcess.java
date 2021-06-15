package com.aladdindb.method.req.search;

import com.aladdindb.MagicLamp;
import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.structure.DataModel;


public class SearchReqProcess <

	UDM 			extends DataModel	< UDM >,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL >,
	SORTER_MODEL 	extends Sorter		< UDM, SORTER_MODEL >
	

> extends ReqProcess < SearchReq < UDM, FINDER_MODEL, SORTER_MODEL >, BlockNavResp , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public SearchReqProcess( int blockSize, FINDER_MODEL finder, SORTER_MODEL sorter , MagicLamp< UDM > magicLamp ) {
		
		var req = new SearchReq < UDM, FINDER_MODEL, SORTER_MODEL >( magicLamp.storeId, blockSize, finder, sorter );
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new SearchReqTransformer	< UDM, FINDER_MODEL, SORTER_MODEL >( magicLamp.udmClass, magicLamp.finderSupport, magicLamp.sorterSupport ) ) ; 
		this.respTransformer	.set ( new BlockNaviRespTransformer() );
		this.magicLamp			.set( magicLamp );
		
		
	}

    //****************************************************************
    //						
    //****************************************************************

}
