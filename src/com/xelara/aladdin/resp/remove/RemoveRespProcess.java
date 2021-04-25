package com.xelara.aladdin.resp.remove;

import com.xelara.aladdin.Genie;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.req.get.by.id.GetByIdReqParser;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.structure.DataModel;
import com.xelara.structure.xml.XML;


public class RemoveRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public RemoveRespProcess( Genie < UDM > genie ) {
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
					genie.units.getUnit( unitID, unit -> {
						if( genie.units.removeUnit( unitID ) ) {
							resp( unit );
						}
					});
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( Unit< UDM > unit ) {
		
		var respParser 	= new RemoveRespParser 	< UDM > ( genie.dataParser );
		var resp 		= new RemoveResp		< UDM > ( unit );
		
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
