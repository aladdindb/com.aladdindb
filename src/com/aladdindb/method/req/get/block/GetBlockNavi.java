package com.aladdindb.method.req.get.block;

import com.aladdindb.UnitsChannel;
import com.aladdindb.filter.Filter;
import com.aladdindb.method.req.ReqModel;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.GetBlockRespModel;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class GetBlockNavi <

	UDM 		extends DataModel 	< UDM >,
	REQ_MODEL 	extends ReqModel	< REQ_MODEL >
	
> implements LineNavigator< GetBlockRespModel >  { 
	
	private GetBlockRespModel currentIdBlock;
	
	private final  UnitsChannel < UDM > unitsChannel;
	
    public GetBlockNavi( 	UnitsChannel	< UDM > 	unitsChannel, 
    						int blockSize, 
    						ReqProcess		< REQ_MODEL , GetBlockRespModel , UDM 	> 	reqProcess ) {
    	
    	this.unitsChannel 	= unitsChannel;
    	
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
