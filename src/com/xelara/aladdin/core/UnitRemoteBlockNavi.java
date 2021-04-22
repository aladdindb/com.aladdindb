package com.xelara.aladdin.core;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.get.all.GetAllReqProcess;
import com.xelara.aladdin.req.get.all.block.GetAllBlockReqProcess;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockResp;
import com.xelara.core.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class UnitRemoteBlockNavi< UDM extends DataModel<UDM> > implements LineNavigator< GetAllBlockResp >  { 
	
	private GetAllBlockResp currentIdBlock;
	
	private final  UnitsChannel<UDM> unitsChannel;
	
    public UnitRemoteBlockNavi( UnitsChannel<UDM> unitsChannel, int blockSize ) {
    	this.unitsChannel 	= unitsChannel;
    	var reqProcess =  new GetAllReqProcess< UDM >( blockSize, unitsChannel );
    	reqProcess.respConsumer.set( this::setCurrentIdBlock );
    	reqProcess.run();
    }

	@Override
	public boolean hasRight() {
		return currentIdBlock.hasRight.get();
	}

	@Override
	public boolean hasLeft() {
		return currentIdBlock.hasLeft.get();
	}

	@Override
	public GetAllBlockResp right() {
		if( currentIdBlock != null ) {
			var reqProcess = new GetAllBlockReqProcess<UDM>( currentIdBlock.cmdSessionID.get(), LineNavigator.DIRECTION.right, this.unitsChannel );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return this.currentIdBlock;
	}

	@Override
	public GetAllBlockResp left() {
		if( currentIdBlock != null ) {
			var reqProcess = new GetAllBlockReqProcess<UDM>( currentIdBlock.cmdSessionID.get(), LineNavigator.DIRECTION.left, this.unitsChannel );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return this.currentIdBlock;
	}
    
	public void setCurrentIdBlock( GetAllBlockResp currentIdBlock ) {
		this.currentIdBlock = currentIdBlock;
	}
	
	public GetAllBlockResp getCurrentIdBlock() {
		return currentIdBlock;
	}
}
