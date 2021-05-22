package com.aladdindb.method.req.close.method.session;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class CloseMethodSessionReqTransformer  extends ReqTransformer< CloseMethodSessionReq >  {
	

    
    private enum ATR { sessionId };
    
    
	public CloseMethodSessionReqTransformer() {
		super( Method.closeSession.method() ); 
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
	public CloseMethodSessionReq newModel() {
		return new CloseMethodSessionReq( null, null );
	}
	
    @Override
    public CloseMethodSessionReq toModel( SnPoint src, CloseMethodSessionReq target ) {
    	super.toModel( src, target );
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.sessionId	,target.sessionId 	);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( CloseMethodSessionReq src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr		.set( ATR.sessionId  	,src.sessionId	);

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
