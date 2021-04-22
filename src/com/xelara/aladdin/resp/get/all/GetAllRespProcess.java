package com.xelara.aladdin.resp.get.all;

import com.xelara.aladdin.Genie;
import com.xelara.aladdin.core.UnitIdBlockMap;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.req.get.all.GetAllReqParser;
import com.xelara.aladdin.req.get.byid.GetByIdReqParser;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.core.Xlr;
import com.xelara.structure.DataModel;
import com.xelara.structure.xml.XML;


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

		var respParser 	= new GetAllBlockRespParser();
		var resp 		= new GetAllBlockResp();

		var blockMap 	= new UnitIdBlockMap( blockSize );
		
		this.genie.units.forEachUnit( unit -> {
			unit.id.get( blockMap::addUnitID );
		});
		
		String cmdSesionID = Xlr.createHashCode( 20 );
		
		var navi = blockMap.createBlockNavi();
		
		genie.unitIdBlockNav.put( cmdSesionID,  navi );
		
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
