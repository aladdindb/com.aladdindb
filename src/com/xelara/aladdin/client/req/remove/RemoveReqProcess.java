package com.xelara.aladdin.client.req.remove;

import com.xelara.aladdin.client.UnitsChannel;
import com.xelara.aladdin.client.req.ReqProcess;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.server.resp.get.byid.GetByIdRespParser;
import com.xelara.structure.xml.XML;



public class RemoveReqProcess < UDM extends DataModel < UDM > > extends ReqProcess< UDM, Unit< UDM > > { 

	
	public final String unitID;
	
	
	public RemoveReqProcess( String unitID, UnitsChannel< UDM > unitsChanel ) {
		super( unitsChanel );
		this.unitID 		= unitID;
	}
	
	@Override
	public void run() {
		
		var req 		= new RemoveReq			( this.unitsChanel.unitGroupID, this.unitID );
		var reqParser 	= new RemoveReqParser	();
		
		reqParser.toNode( req, reqNode -> {
			XML.parse( reqNode, reqStr -> {
				unitsChanel.channel.sendReq( reqStr, this :: useResp );
			});
		});
	}

	private void useResp( String respStr ) {
		System.out.println( respStr );
		XML.parse( respStr, respNode -> {
//			new GetByIdRespParser < UDM > ( new UnitParser < UDM > ( unitsChanel.unitDataParser ) ).toModel( respNode, resp -> {
//				resp.unit.get( this.respConsumer );
//			});
		});
	}
	
}
