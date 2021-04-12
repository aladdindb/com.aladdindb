package com.xelara.aladdin.client.req.add;

import com.xelara.aladdin.client.UnitsChannel;
import com.xelara.aladdin.client.req.ReqProcess;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.server.resp.add.AddRespParser;
import com.xelara.structure.xml.XML;

public class AddReqProcess < UDM extends DataModel < UDM > > extends ReqProcess< UDM, String > { 

	public final UDM unitData;

	public AddReqProcess( UDM unitData, UnitsChannel< UDM > unitsChanel ) {
		super( unitsChanel );
		this.unitData 		= unitData;
	}
	
	@Override
	public void run() {
		
		var req 		= new AddReq		<UDM> ( this.unitsChanel.unitGroupID, this.unitData );
		var reqParser 	= new AddReqParser	<UDM> ( this.unitsChanel.unitDataParser );
		
		reqParser.toNode( req, reqNode -> {
			XML.parse( reqNode, reqStr -> {
				unitsChanel.channel.sendReq( reqStr, this :: useResp );
			});
		});
	}
	
	private void useResp( String respStr ) {
		XML.parse( respStr, respNode -> {
			new AddRespParser().toModel(respNode, respModel -> {
				respModel.unitID.get( this.respConsumer );
			});
		});
	}
	
}
