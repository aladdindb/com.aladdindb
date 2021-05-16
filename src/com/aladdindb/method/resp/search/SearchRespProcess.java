package com.aladdindb.method.resp.search;

import com.aladdindb.Genie;
import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.search.SearchReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.UnitsIdBlockStorage;
import com.aladdindb.util.Util;


public class SearchRespProcess <

	UDM 			extends Store 	< UDM >, 
	FINDER_MODEL 	extends Finder	< UDM, FINDER_MODEL >,
	SORTER_MODEL	extends Sorter	< UDM, SORTER_MODEL >

> extends RespProcess< UDM > { 


	public SearchRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			var transformer = new SearchReqTransformer< UDM, FINDER_MODEL, SORTER_MODEL >( genie.finderSupport,genie.sorterSupport );
			transformer.toStore( reqNode, req -> {
				
				var blockSize 	= req.blockSize	.get();
				var finder 		= req.finder	.get();
				var sorter 		= req.sorter	.get();
				
				this.resp( blockSize, finder, sorter );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( int blockSize, Finder finder, Sorter sorter ) {

		var respParser 	= new BlockNaviRespTransformer();
		var resp 		= new BlockNavResp();

		var blockMap 	= new UnitsIdBlockStorage( blockSize );
		
		this.genie.units.search( finder, sorter, unit -> {
			unit.id.get( blockMap::addUnitID );
		});
		
		String cmdSesionID = Util.createAlphaNumericInclUpperCaseHashCode( 20 );
		
		var navi = blockMap.createBlockNavi();
		
		genie.unitsIdBlockNaviMap.put( cmdSesionID,  navi );
		
		resp.methodSessionID	.set( cmdSesionID );
		
		resp.unitsIdBlock		.set( navi.right());
		
		resp.hasLeft			.set( navi.hasLeft() );
		resp.hasRight			.set( navi.hasRight());
		
		respParser.toNode( resp, respNode -> {
			this.genie.respConsumer.get( respConsumer -> {
				XML.parse( respNode, respConsumer );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
