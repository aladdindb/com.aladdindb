package com.aladdindb.resp.get;

import com.aladdindb.Genie;
import com.aladdindb.filter.Filter;
import com.aladdindb.req.get.all.GetAllReqParser;
import com.aladdindb.req.get.by.filter.GetByFilterReqParser;
import com.aladdindb.req.get.by.id.GetByIdReqParser;
import com.aladdindb.resp.RespProcess;
import com.aladdindb.resp.get.block.GetBlockRespModel;
import com.aladdindb.resp.get.block.GetBlockRespParser;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.UnitIdBlockMap;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitParser;
import com.aladdindb.util.Ald;


public class GetByFilterRespProcess <

	UDM 			extends DataModel 	< UDM >, 
	FILTER_MODEL 	extends Filter		< UDM, FILTER_MODEL >

> extends RespProcess< UDM > { 


	public GetByFilterRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			var p = new GetByFilterReqParser< UDM, FILTER_MODEL >( genie.filterFac );
			p.toModel( reqNode, req -> {
				req.blockSize.get( blockSize -> {
					req.filter.get( filter -> {
						this.resp( blockSize, filter);
					});
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( int blockSize, Filter filter) {

		var respParser 	= new GetBlockRespParser();
		var resp 		= new GetBlockRespModel();

		var blockMap 	= new UnitIdBlockMap( blockSize );
		
		this.genie.units.forEachUnit( filter, unit -> {
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
