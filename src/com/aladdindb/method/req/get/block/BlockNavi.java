package com.aladdindb.method.req.get.block;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.Req;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.structure.Store;
import com.aladdindb.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class BlockNavi <

	UDM 		extends Store 	< UDM >,
	REQ_MODEL 	extends Req	< REQ_MODEL >
	
> implements LineNavigator < BlockNavResp > { 
	
	private BlockNavResp currentIdBlock;
	
	private final  MagicLamp < UDM > magicLamp;

    public BlockNavi( BlockNavResp blockResp, MagicLamp < UDM > magicLamp ) {
    	this.currentIdBlock = blockResp;
		this.magicLamp 		= magicLamp;
    }
	
    public BlockNavi( 	MagicLamp< UDM > magicLamp, int blockSize, 
    						ReqProcess< REQ_MODEL , BlockNavResp , UDM > reqProcess ) {
    	
    	this.magicLamp 	= magicLamp;
    	
    	reqProcess.respConsumer.set( this::setCurrentIdBlock );
    	reqProcess.run();
    }

	@Override
	public boolean hasRight() {
		return this.currentIdBlock != null ?  currentIdBlock.hasRight.get() : false;
	}

	@Override
	public boolean hasLeft() {
		return this.currentIdBlock != null ? currentIdBlock.hasLeft.get() : false;
	}

	@Override
	public BlockNavResp right() {
		if( currentIdBlock != null ) {
			var reqProcess = new BlockNaviReqProcess < UDM > ( currentIdBlock.methodSessionID.get(), LineNavigator.DIRECTION.right, this.magicLamp );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return currentIdBlock;
	}

	@Override
	public BlockNavResp left() {
		if( currentIdBlock != null ) {
			var reqProcess = new BlockNaviReqProcess<UDM>( currentIdBlock.methodSessionID.get(), LineNavigator.DIRECTION.left, this.magicLamp );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return currentIdBlock;
	}
    
	public void setCurrentIdBlock( BlockNavResp currentIdBlock ) {
		this.currentIdBlock = currentIdBlock;
	}
	
	public BlockNavResp getCurrentIdBlock() {
		return currentIdBlock;
	}
}
