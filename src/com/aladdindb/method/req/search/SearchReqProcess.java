package com.aladdindb.method.req.search;

import com.aladdindb.FinderSupport;
import com.aladdindb.SorterSupport;
import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.structure.DataModel;


public class SearchReqProcess <

	UDM 			extends DataModel	< UDM >,
	FINDER_MODEL 	extends Finder	< UDM, FINDER_MODEL >,
	SORTER_MODEL 	extends Sorter	< UDM, SORTER_MODEL >
	

> extends ReqProcess < SearchReq < UDM, FINDER_MODEL, SORTER_MODEL >, BlockNavResp , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public SearchReqProcess( String storeId, int blockSize, FINDER_MODEL finder, SORTER_MODEL sorter,  FinderSupport < UDM > magicLamp, SorterSupport< UDM > sorterSupport ) {
		
//		this.magicLamp.set ( magicLamp);

		var req = new SearchReq < UDM, FINDER_MODEL, SORTER_MODEL >( storeId, blockSize, finder, sorter );
		
		this.req				.set ( req );
		this.reqTransformer		.set ( new SearchReqTransformer	< UDM, FINDER_MODEL, SORTER_MODEL >( magicLamp, sorterSupport ) ); 
		this.respTransformer	.set ( new BlockNaviRespTransformer() );
	}

    //****************************************************************
    //						
    //****************************************************************

}
