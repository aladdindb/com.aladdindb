package com.aladdindb.resp.remove;

import com.aladdindb.Genie;
import com.aladdindb.req.get.by.id.GetByIdReqParser;
import com.aladdindb.resp.RespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitParser;


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
