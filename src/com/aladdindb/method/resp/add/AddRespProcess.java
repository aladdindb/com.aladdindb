package com.aladdindb.method.resp.add;

import com.aladdindb.Genie;
import com.aladdindb.method.req.add.AddReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;

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
			new AddReqTransformer < UDM > ( genie.support.newDataTransformer() ).toModel( reqNode, req -> {
				req.unitData.get( unitData -> { 
					String unitID = genie.store.addUnit( unitData );
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
		new AddRespTransformer().toNode( resp, respNode -> {
			this.genie.respConsumer.get( respConsumer -> {
				XML.parse( respNode, respConsumer );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
