package com.aladdindb.method.resp.get;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.all.GetAllReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.UnitsIdBlockStorage;
import com.aladdindb.util.Util;


public class GetAllRespProcess < UDM extends Store < UDM > > extends RespProcess< UDM > { 


	public GetAllRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new GetAllReqTransformer().toStore( reqNode, req -> {
				req.blockSize.get( this :: resp );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( int blockSize ) {

		var respTransformer = new BlockNaviRespTransformer();
		var resp 			= new BlockNavResp();

		var blockStorage 	= new UnitsIdBlockStorage( blockSize );
		
		this.genie.units.forEachUnit( unit -> {
			unit.id.get( blockStorage::addUnitID );
		});
		
		String cmdSesionID = Util.createAlphaNumericInclUpperCaseHashCode( 20 );
		
		var navi = blockStorage.createBlockNavi();
		
		genie.unitsIdBlockNaviMap.put( cmdSesionID,  navi );
		
		resp.methodSessionID	.set( cmdSesionID );
		
		resp.unitsIdBlock	.set( navi.right());
		
		resp.hasLeft		.set( navi.hasLeft() );
		resp.hasRight		.set( navi.hasRight());
		
		respTransformer.toNode( resp, respNode -> {
			this.genie.respConsumer.get( respConsumer -> {
				XML.parse( respNode, respConsumer );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
