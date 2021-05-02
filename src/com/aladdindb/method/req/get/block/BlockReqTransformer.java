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
public final class BlockReqTransformer  extends ReqTransformer< BlockReq >  {
	

    
    private enum ATR { cmdSessionID, direction };
    
    
	public BlockReqTransformer() {
		super( Method.GET_ALL_BLOCK.reqTagName() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public BlockReq newModel() {
		return new BlockReq( null, null, null );
	}
	
    @Override
    public BlockReq toModel( SnPoint src, BlockReq target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.cmdSessionID	,target.cmdSessionID 	);
    	srcAtr.asEnum	.get( ATR.direction		,target.direction, LineNavigator.DIRECTION.class );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( BlockReq src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr		.set( ATR.cmdSessionID  ,src.cmdSessionID	);
    	targetAtr.asEnum	.set( ATR.direction  	,src.direction, LineNavigator.DIRECTION.class ); 

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
