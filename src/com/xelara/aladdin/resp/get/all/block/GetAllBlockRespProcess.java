package com.xelara.aladdin.resp.get.all.block;

import com.xelara.aladdin.Genie;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.UnitIdBlockMap;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.req.get.all.block.GetAllBlockReqParser;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.core.Xlr;
import com.xelara.core.util.LineNavigator;
import com.xelara.structure.xml.XML;


public class GetAllBlockRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public GetAllBlockRespProcess( Genie < UDM > genie ) {
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

		var respParser 	= new GetAllBlockRespParser();
		var resp 		= new GetAllBlockResp();

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
