package com.aladdindb.method.req.get.block;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;
import com.aladdindb.util.LineNavigator;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetAllBlockReqParser  extends ReqParser< GetAllBlockReqModel >  {
	

    
    private enum ATR { cmdSessionID, direction };
    
    
	public GetAllBlockReqParser() {
		super( Method.GET_ALL_BLOCK.req() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetAllBlockReqModel newModel() {
		return new GetAllBlockReqModel( null, null, null );
	}
	
    @Override
    public GetAllBlockReqModel toModel( SnPoint src, GetAllBlockReqModel target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.cmdSessionID	,target.cmdSessionID 	);
    	srcAtr.asEnum	.get( ATR.direction		,target.direction, LineNavigator.DIRECTION.class );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetAllBlockReqModel src, SnPoint target ) {
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
