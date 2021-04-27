package com.xelara.aladdin.resp.get.block;

import com.xelara.aladdin.Genie;
import com.xelara.aladdin.core.UnitIdBlockMap;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.req.get.block.GetAllBlockReqParser;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.core.Xlr;
import com.xelara.core.util.LineNavigator;
import com.xelara.structure.DataModel;
import com.xelara.structure.xml.XML;


public class GetBlockRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public GetBlockRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new GetAllBlockReqParser().toModel( reqNode, req -> {
				req.cmdSessionID.get( cmdSessionID -> {
					req.direction.get( direction -> {
						this.resp(cmdSessionID, direction);
					});
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( String cmdSessionID, LineNavigator.DIRECTION direction ) {

		var respParser 	= new GetBlockRespParser();
		var resp 		= new GetBlockRespModel();

		var nav = genie.unitIdBlockNav.get( cmdSessionID );
		
		switch( direction ) {
			case left	:resp.unitsIdBlock.set( nav.left())		;break; 
			case right	:resp.unitsIdBlock.set( nav.right())	;break;
		}
		
		resp.cmdSessionID	.set( cmdSessionID );
		
		resp.hasLeft		.set( nav.hasLeft() );
		resp.hasRight		.set( nav.hasRight());
		
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
