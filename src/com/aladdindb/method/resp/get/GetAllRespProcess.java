package com.aladdindb.method.resp.get;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.all.GetAllReqTransformer;
import com.aladdindb.method.req.get.by.id.GetByIdReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.get.block.BlockResp;
import com.aladdindb.method.resp.get.block.BlockRespTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.UnitIdBlockMap;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitParser;
import com.aladdindb.util.Ald;


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

		var respParser 	= new BlockRespTransformer();
		var resp 		= new BlockResp();

		var blockMap 	= new UnitIdBlockMap( blockSize );
		
		this.genie.units.forEachUnit( unit -> {
			unit.id.get( blockMap::addUnitID );
		});
		
		String cmdSesionID = Ald.createHashCode( 20 );
		
		var navi = blockMap.createBlockNavi();
		
		genie.unitIdBlockNaviMap.put( cmdSesionID,  navi );
		
		resp.cmdSessionID	.set( cmdSesionID );
		
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
