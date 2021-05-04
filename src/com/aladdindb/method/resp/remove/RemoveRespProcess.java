package com.aladdindb.method.resp.remove;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.by.id.GetByIdReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.models.Unit;


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
			new GetByIdReqTransformer().toModel( reqNode, req -> {
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
		
		var respParser 	= new RemoveRespTransformer 	< UDM > ( genie.dataTransformer );
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
