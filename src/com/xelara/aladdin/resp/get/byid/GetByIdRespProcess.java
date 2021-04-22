package com.xelara.aladdin.resp.get.byid;

import com.xelara.aladdin.Genie;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.req.get.byid.GetByIdReqParser;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.structure.xml.XML;


public class GetByIdRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public GetByIdRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new GetByIdReqParser().toModel( reqNode, req -> {
				req.unitID.get( unitID -> {
					genie.units.getUnit( unitID, this :: resp );
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( Unit< UDM > unit ) {
		
		var respParser 	= new GetByIdRespParser < UDM >( genie.dataParser );
		var resp 		= new GetByIdResp<>(unit);
		
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
