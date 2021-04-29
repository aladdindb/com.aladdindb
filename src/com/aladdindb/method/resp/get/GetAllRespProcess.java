package com.aladdindb.method.resp.get;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.all.GetAllReqParser;
import com.aladdindb.method.req.get.by.id.GetByIdReqParser;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.get.block.GetBlockRespModel;
import com.aladdindb.method.resp.get.block.GetBlockRespParser;
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
			new GetAllReqParser().toModel( reqNode, req -> {
				req.blockSize.get( this :: resp );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( int blockSize ) {

		var respParser 	= new GetBlockRespParser();
		var resp 		= new GetBlockRespModel();

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
