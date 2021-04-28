package com.aladdindb.resp.add;

import com.aladdindb.Genie;
import com.aladdindb.req.add.AddReqParser;
import com.aladdindb.resp.RespProcess;
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
		var resp = new AddRespModel( unitID );
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
