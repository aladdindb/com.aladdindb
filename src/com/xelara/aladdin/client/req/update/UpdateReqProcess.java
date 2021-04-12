package com.xelara.aladdin.client.req.update;

import com.xelara.aladdin.client.UnitsChannel;
import com.xelara.aladdin.client.req.ReqProcess;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.server.resp.add.AddRespParser;
import com.xelara.aladdin.server.resp.update.UpdateRespParser;
import com.xelara.structure.xml.XML;

public class UpdateReqProcess < UDM extends DataModel < UDM > > extends ReqProcess< UDM, String > { 

	public final Unit< UDM > unit;
	
	public UpdateReqProcess( Unit< UDM > unit, UnitsChannel< UDM > unitsChanel ) {
		super( unitsChanel );
		
		this.unit = unit;
	}
	
	@Override
	public void run() {
		
		var req 		= new UpdateReq			<UDM> ( this.unitsChanel.unitGroupID, this.unit );
		var reqParser 	= new UpdateReqParser	<UDM> ( this.unitsChanel.unitDataParser  );
		
		reqParser.toNode( req, reqNode -> {
			XML.parse( reqNode, reqStr -> {
				unitsChanel.channel.sendReq( reqStr, this :: useResp );
			});
		});
	}
	
	private void useResp( String respStr ) {
		System.out.println( respStr );
		XML.parse( respStr, respNode -> {
			new UpdateRespParser().toModel(respNode, respModel -> {
				respModel.unitID.get( this.respConsumer );
			});
		});
	}
	
}
