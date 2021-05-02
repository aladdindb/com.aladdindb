package com.aladdindb.method.resp.get.block;

import com.aladdindb.Genie;
import com.aladdindb.method.req.get.block.BlockReqTransformer;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.units.UnitIdBlockMap;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Ald;
import com.aladdindb.util.LineNavigator;


public class BlockRespProcess < UDM extends DataModel < UDM > > extends RespProcess< UDM > { 


	public BlockRespProcess( Genie < UDM > genie ) {
		super( genie );
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void run() {
		genie.reqNode.get( reqNode -> {
			new BlockReqTransformer().toModel( reqNode, req -> {
				req.cmdSessionID.get( cmdSessionID -> {
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

		var respParser 	= new BlockRespTransformer();
		var resp 		= new BlockResp();

		var nav = genie.unitIdBlockNaviMap.get( cmdSessionID );
		
		switch( direction ) {
			case left	:resp.unitsIdBlock.set( nav.left())		;break; 
			case right	:resp.unitsIdBlock.set( nav.right())	;break;
		}
		
		resp.cmdSessionID	.set( cmdSessionID );
		
		resp.hasLeft		.set( nav.hasLeft() );
		resp.hasRight		.set( nav.hasRight());
		
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
