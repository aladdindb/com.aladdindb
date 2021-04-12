package com.xelara.aladdin.server.resp.update;

import com.xelara.aladdin.client.req.update.UpdateReqParser;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.server.Genie;
import com.xelara.aladdin.server.resp.RespProcess;
import com.xelara.structure.xml.XML;

public class UpdateRespProcess < UGM extends DataModel < UGM > > extends RespProcess< UGM > { 


	public UpdateRespProcess( Genie < UGM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new UpdateReqParser < UGM >( genie.dataParser ).toModel( reqNode, req -> {
				req.unitData.get( unitData -> { 
					if( genie.units.updateUnit( unitData ) ) {
						unitData.id.get( this::resp );
					}
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( String unitID ) {
		var resp = new UpdateResp( unitID );
		new UpdateRespParser().toNode( resp, respNode -> {
			XML.parse( respNode, this.genie.respConsumer );
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
