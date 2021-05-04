package com.aladdindb.method.resp.get;

import com.aladdindb.Genie;
import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.get.by.finder.GetByFinderReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.UnitsIdBlockStorage;
import com.aladdindb.util.Util;


public class GetByFinderRespProcess <

	UDM 			extends DataModel 	< UDM >, 
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL >

> extends RespProcess< UDM > { 


	public GetByFinderRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			var p = new GetByFinderReqTransformer< UDM, FINDER_MODEL >( genie.finderSupplier );
			p.toModel( reqNode, req -> {
				req.blockSize.get( blockSize -> {
					req.finder.get( filter -> {
						this.resp( blockSize, filter);
					});
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( int blockSize, Finder finder) {

		var respParser 	= new BlockNaviRespTransformer();
		var resp 		= new BlockNavResp();

		var blockMap 	= new UnitsIdBlockStorage( blockSize );
		
		this.genie.units.forEachUnit( finder, unit -> {
			unit.id.get( blockMap::addUnitID );
		});
		
		String cmdSesionID = Util.createAlphaNumericInclUpperCaseHashCode( 20 );
		
		var navi = blockMap.createBlockNavi();
		
		genie.unitsIdBlockNaviMap.put( cmdSesionID,  navi );
		
		resp.methodSessionID	.set( cmdSesionID );
		
		resp.unitsIdBlock	.set( navi.right());
		
		resp.hasLeft		.set( navi.hasLeft() );
		resp.hasRight		.set( navi.hasRight());
		
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
