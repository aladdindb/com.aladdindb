package com.aladdindb.method.resp.search;

import com.aladdindb.Genie;
import com.aladdindb.finder.DefaultFinderTransformer;
import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupport;
import com.aladdindb.finder.logical.LogicalAndFinders;
import com.aladdindb.finder.logical.LogicalAndFindersTransformer;
import com.aladdindb.method.req.search.SearchReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.sorter.SorterSupport;
import com.aladdindb.store.UnitIdBlock;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.util.Util;


public class SearchRespProcess <

	UDM 			extends DataModel 	< UDM >, 
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL >,
	SORTER_MODEL	extends Sorter		< UDM, SORTER_MODEL >

> extends RespProcess< UDM > { 

		private final FinderSupport< UDM > finderSupport; 
		private final SorterSupport< UDM > sorterSupport;
		
	public SearchRespProcess( Genie<UDM> genie, FinderSupport< UDM > finderSupport, SorterSupport< UDM > sorterSupport ) {
		super( genie );
		this.finderSupport = finderSupport;
		this.sorterSupport = sorterSupport;
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			var transformer = new SearchReqTransformer< UDM, FINDER_MODEL, SORTER_MODEL >( this.genie.udmClass, this.finderSupport, this.sorterSupport );
			transformer.toModel( reqNode, req -> {
				
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

		var respTransformer 	= new BlockNaviRespTransformer();
		var resp 				= new BlockNavResp();

		var unitIdBlock			= new UnitIdBlock( blockSize );

//		var t = new LogicalAndFindersTransformer<>( this.finderSupport );
//		var node = t.toNode( (LogicalAndFinders) finder);
//		System.out.println( XML.toString( node ) );
		
		
		this.genie.store.search( finder, sorter, unit -> {
			unit.id.get( unitIdBlock::addUnitId );
		});
		
		String methodSessionId = Util.createAlphaNumericInclUpperCaseHashCode( 20 );
		
		var navi = unitIdBlock.newUnitIdBlockNavi();
		
		genie.unitsIdBlockNaviMap.put( methodSessionId,  navi );
		
		resp.methodSessionId	.set( methodSessionId );
		
		resp.unitsIdBlock		.set( navi.right());
		
		resp.hasLeft			.set( navi.hasLeft() );
		resp.hasRight			.set( navi.hasRight());
		
		respTransformer.toNode( resp, respNode -> {
			this.genie.respConsumer.get( respConsumer -> {
				XML.toString( respNode, respConsumer );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
