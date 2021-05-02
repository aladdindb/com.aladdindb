package com.aladdindb.method.req.get.block;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;
import com.aladdindb.util.LineNavigator;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class BlockNaviReqTransformer  extends ReqTransformer< BlockNaviReq >  {
	

    
    private enum ATR { methodSessionID, direction };
    
    
	public BlockNaviReqTransformer() {
		super( Method.GET_BLOCK.reqTagName() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public BlockNaviReq newModel() {
		return new BlockNaviReq( null, null, null );
	}
	
    @Override
    public BlockNaviReq toModel( SnPoint src, BlockNaviReq target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.methodSessionID	,target.methodSessionID 	);
    	srcAtr.asEnum	.get( ATR.direction			,target.direction, LineNavigator.DIRECTION.class );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( BlockNaviReq src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr		.set( ATR.methodSessionID  	,src.methodSessionID	);
    	targetAtr.asEnum	.set( ATR.direction  		,src.direction, LineNavigator.DIRECTION.class ); 

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
