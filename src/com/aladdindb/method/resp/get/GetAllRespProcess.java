package com.aladdindb.method.resp.get;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.all.GetAllReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.block.BlockNaviRespTransformer;
import com.aladdindb.store.UnitIdBlock;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.util.Util;


public class GetAllRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public GetAllRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new GetAllReqTransformer().toModel( reqNode, req -> {
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

		var unitIdBlock 	= new UnitIdBlock( blockSize );
		
		this.genie.store.forEachUnit( unit -> {
			unit.id.get( unitIdBlock::addUnitId );
		});
		
		String cmdSesionID = Util.createAlphaNumericInclUpperCaseHashCode( 20 );
		
		var navi = unitIdBlock.newUnitIdBlockNavi();
		
		genie.unitsIdBlockNaviMap.put( cmdSesionID,  navi );
		
		resp.methodSessionId	.set( cmdSesionID );
		
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
