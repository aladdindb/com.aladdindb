package com.aladdindb.method.resp.get.by.id;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.by.id.GetByIdReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;


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
			new GetByIdReqTransformer().toModel( reqNode, req -> {
				req.unitId.get( unitID -> {
					genie.store.get( unitID, this :: resp );
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( Unit< UDM > unit ) {
		
		var respParser 	= new GetByIdRespTransformer < UDM >( genie.support.newDataTransformer() );
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
