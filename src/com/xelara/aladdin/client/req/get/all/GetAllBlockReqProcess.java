package com.xelara.aladdin.client.req.get.all;

import com.xelara.aladdin.client.UnitsChannel;
import com.xelara.aladdin.client.req.ReqProcess;
import com.xelara.aladdin.client.req.get.all.block.GetAllBlockReq;
import com.xelara.aladdin.client.req.get.all.block.GetAllBlockReqParser;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.server.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.server.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.aladdin.server.resp.get.byid.GetByIdRespParser;
import com.xelara.core.util.LineNavigator;
import com.xelara.core.util.Var;
import com.xelara.structure.xml.XML;



public class GetAllBlockReqProcess < UDM extends DataModel < UDM > > extends ReqProcess< UDM, GetAllBlockResp > { 

	
	private final String cmdSessionID;
	private final LineNavigator.DIRECTION direction;
	
	public GetAllBlockReqProcess( String cmdSessionID, LineNavigator.DIRECTION direction,  UnitsChannel< UDM > unitsChanel ) {
		super( unitsChanel );
		this.cmdSessionID 	= cmdSessionID; 
		this.direction 		= direction;
	}
	
	@Override
	public void run() {
		var req 		= new GetAllBlockReq		( this.unitsChanel.unitGroupID, this.cmdSessionID, this.direction.name() );
		var reqParser 	= new GetAllBlockReqParser	();
		
		reqParser.toNode( req, reqNode -> {
			XML.parse( reqNode, reqStr -> {
				unitsChanel.channel.sendReq( reqStr, this :: useResp );
			});
		});
	}

	private void useResp( String respStr ) {
		System.out.println( respStr );
		XML.parse( respStr, respNode -> {
			new GetAllBlockRespParser().toModel( respNode,  resp -> {
				this.respConsumer.get( consum -> {
					consum.accept( resp );
				});
			});
		});
	}
	
}
