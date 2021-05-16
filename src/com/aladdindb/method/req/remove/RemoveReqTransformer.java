package com.aladdindb.method.req.remove;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class RemoveReqTransformer  extends ReqTransformer< RemoveReq >  {
	

    
    private enum ATR { unitID };
    
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveReqTransformer() {
		super( Method.REMOVE.reqTagName() );
	}
    
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public RemoveReq newStore() {
		return new RemoveReq( null, null );
	}
	
    @Override
    public RemoveReq toStore( SnPoint src, RemoveReq target ) {
    	super.toStore( src, target );
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitID ,target.unitID );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( RemoveReq src, SnPoint target ) {
    	super.toNode( src, target );
    	
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitID ,src.unitID	);

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
