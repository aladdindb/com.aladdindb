package com.aladdindb.method.resp.get.block;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.block.BlockNaviReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.util.LineNavigator;


public class BlockNaviRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public BlockNaviRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new BlockNaviReqTransformer().toModel( reqNode, req -> {
				req.methodSessionID.get( cmdSessionID -> {
					req.direction.get( direction -> {
						this.resp(cmdSessionID, direction);
					});
				});
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
	private void resp( String cmdSessionID, LineNavigator.DIRECTION direction ) {

		var respParser 	= new BlockNaviRespTransformer();
		var resp 		= new BlockNavResp();

		var nav = genie.unitsIdBlockNaviMap.get( cmdSessionID );
		
		if( nav != null ) {
			switch( direction ) {
				case left	:resp.unitsIdBlock.set( nav.left())		;break; 
				case right	:resp.unitsIdBlock.set( nav.right())	;break;
			}
		
			resp.methodSessionId	.set( cmdSessionID );
			
			resp.hasLeft		.set( nav.hasLeft() );
			resp.hasRight		.set( nav.hasRight());
		} else {
			resp.unitsIdBlock.set( "" );
			resp.hasLeft.set( false );
			resp.hasRight.set( false );
			resp.methodSessionId.set("");
		}
		
		respParser.toNode( resp, respNode -> {
			this.genie.respConsumer.get( respConsumer -> {
				XML.toString( respNode, respConsumer );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
}
