package com.xelara.aladdin.resp.update;

import com.xelara.aladdin.Genie;
import com.xelara.aladdin.req.update.UpdateReqParser;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.structure.DataModel;
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
