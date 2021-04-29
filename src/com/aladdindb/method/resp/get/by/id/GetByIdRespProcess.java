package com.aladdindb.method.resp.get.by.id;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.by.id.GetByIdReqParser;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.models.Unit;


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
		var resp 		= new GetByIdRespModel<>(unit);
		
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
