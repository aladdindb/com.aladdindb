package com.aladdindb.method.resp.close.method.session;

import com.aladdindb.Genie;
import com.aladdindb.method.req.close.method.session.CloseMethodSessionReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.xml.XML;


public class CloseMethodSessionRespProcess < UDM extends Store < UDM > > extends RespProcess< UDM > { 


	public CloseMethodSessionRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new CloseMethodSessionReqTransformer().toStore( reqNode, req -> {
				req.methodSessionID.get( methodSessionID -> {
					this.resp( methodSessionID );
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( String methodSessionID ) {

		var respTransformer 	= new CloseMethodSessionRespTransformer();
		var resp 				= new CloseMethodSessionResp();

		var nav = genie.unitsIdBlockNaviMap.remove( methodSessionID );
		
		resp.successful.set( nav != null );
		
		respTransformer.toNode( resp, respNode -> {
			this.genie.respConsumer.get( respConsumer -> {
				XML.parse( respNode, respConsumer );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
