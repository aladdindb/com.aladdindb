package com.xelara.aladdin.client.req.get.all;

import com.xelara.aladdin.client.UnitsChannel;
import com.xelara.aladdin.client.req.ReqProcess;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.server.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.server.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.aladdin.server.resp.get.byid.GetByIdRespParser;
import com.xelara.core.util.Var;
import com.xelara.structure.xml.XML;



public class GetAllReqProcess < UDM extends DataModel < UDM > > extends ReqProcess< UDM, GetAllBlockResp > { 

	
	public final int blockSize;
	
	
	public GetAllReqProcess( int blockSize, UnitsChannel< UDM > unitsChanel ) {
		super( unitsChanel );
		this.blockSize = blockSize;
	}
	
	@Override
	public void run() {
		
		var req 		= new GetAllReq			( this.unitsChanel.unitGroupID, this.blockSize );
		var reqParser 	= new GetAllReqParser	();
		
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
