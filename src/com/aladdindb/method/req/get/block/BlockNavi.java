package com.aladdindb.method.req.get.block;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.Req;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.block.BlockResp;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class BlockNavi <

	UDM 		extends DataModel 	< UDM >,
	REQ_MODEL 	extends Req	< REQ_MODEL >
	
> implements LineNavigator < BlockResp > { 
	
	private BlockResp currentIdBlock;
	
	private final  MagicLamp < UDM > magicLamp;
	
    public BlockNavi( 	MagicLamp< UDM > magicLamp, int blockSize, 
    						ReqProcess< REQ_MODEL , BlockResp , UDM > reqProcess ) {
    	
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
	public BlockResp right() {
		if( currentIdBlock != null ) {
			var reqProcess = new BlockReqProcess < UDM > ( currentIdBlock.cmdSessionID.get(), LineNavigator.DIRECTION.right, this.magicLamp );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return currentIdBlock;
	}

	@Override
	public BlockResp left() {
		if( currentIdBlock != null ) {
			var reqProcess = new BlockReqProcess<UDM>( currentIdBlock.cmdSessionID.get(), LineNavigator.DIRECTION.left, this.magicLamp );
			reqProcess.respConsumer.set( this :: setCurrentIdBlock );
	    	reqProcess.run();
		}
		return currentIdBlock;
	}
    
	public void setCurrentIdBlock( BlockResp currentIdBlock ) {
		this.currentIdBlock = currentIdBlock;
	}
	
	public BlockResp getCurrentIdBlock() {
		return currentIdBlock;
	}
}
