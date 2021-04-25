package com.xelara.aladdin.core;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.req.get.all.block.GetAllBlockReqProcess;
import com.xelara.aladdin.req.get.by.filter.GetByFilterReqProcess;
import com.xelara.aladdin.resp.get.block.GetBlockRespModel;
import com.xelara.core.util.LineNavigator;
import com.xelara.structure.DataModel;

/**
*
* @author Macit Kandemir
*/
public class UnitRemoteBlockNaviByFilter <

	UDM 			extends DataModel 	< UDM >

> implements LineNavigator< GetBlockRespModel >  { 
	
	private GetBlockRespModel currentIdBlock;
	
	private final  UnitsChannel<UDM> unitsChannel;
	
    public UnitRemoteBlockNaviByFilter( UnitsChannel<UDM> unitsChannel, int blockSize, Filter filter ) {
    	this.unitsChannel 	= unitsChannel;
    	var reqProcess =  new GetByFilterReqProcess<>( blockSize, filter, unitsChannel );
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
	public GetBlockRespModel right() {
		if( currentIdBlock != null ) {
			var reqProcess = new GetAllBlockReqProcess<UDM>( currentIdBlock.cmdSessionID.get(), LineNavigator.DIRECTION.right, this.unitsChannel );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return this.currentIdBlock;
	}

	@Override
	public GetBlockRespModel left() {
		if( currentIdBlock != null ) {
			var reqProcess = new GetAllBlockReqProcess<UDM>( currentIdBlock.cmdSessionID.get(), LineNavigator.DIRECTION.left, this.unitsChannel );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return this.currentIdBlock;
	}
    
	public void setCurrentIdBlock( GetBlockRespModel currentIdBlock ) {
		this.currentIdBlock = currentIdBlock;
	}
	
	public GetBlockRespModel getCurrentIdBlock() {
		return currentIdBlock;
	}
}
