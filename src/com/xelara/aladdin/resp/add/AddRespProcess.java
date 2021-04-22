package com.xelara.aladdin.resp.add;

import com.xelara.aladdin.Genie;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.req.add.AddReqParser;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.structure.xml.XML;

public class AddRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public AddRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new AddReqParser < UDM > ( genie.dataParser ).toModel( reqNode, req -> {
				req.unitData.get( unitData -> { 
					String unitID = genie.units.addUnit( unitData );
					resp( unitID );
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( String unitID ) {
		var resp = new AddResp( unitID );
		new AddRespParser().toNode( resp, respNode -> {
			this.genie.respConsumer.get( respConsumer -> {
				XML.parse( respNode, respConsumer );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
