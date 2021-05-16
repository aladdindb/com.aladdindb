package com.aladdindb.method.req.get.all;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetAllReqTransformer  extends ReqTransformer< GetAllReq >  {
	

    
    private enum ATR { blockSize };
    
    
	public GetAllReqTransformer() {
		super( Method.GET_ALL.reqTagName() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
	public GetAllReq newStore() {
		return new GetAllReq( null, 0 );
	}
	
    @Override
    public GetAllReq toStore( SnPoint src, GetAllReq target ) {
    	super.toStore(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asInt.get( ATR.blockSize, target.blockSize );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetAllReq src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asInt.set( ATR.blockSize, src.blockSize );

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
