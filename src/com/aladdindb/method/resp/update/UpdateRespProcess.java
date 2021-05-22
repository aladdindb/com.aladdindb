package com.aladdindb.method.resp.update;

import com.aladdindb.Genie;
import com.aladdindb.method.req.update.UpdateReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;

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
			new UpdateReqTransformer < UGM >( genie.support.newDataTransformer() ).toModel( reqNode, req -> {
				req.unitData.get( unitData -> { 
					if( genie.store.update( unitData ) ) {
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
		new UpdateRespTransformer().toNode( resp, respNode -> {
			XML.parse( respNode, this.genie.respConsumer );
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
