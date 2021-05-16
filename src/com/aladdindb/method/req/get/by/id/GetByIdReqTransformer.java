package com.aladdindb.method.req.get.by.id;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByIdReqTransformer  extends ReqTransformer< GetByIdReq >  {
	

    
    private enum ATR { unitID };
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdReqTransformer() {
		super( Method.GET_BY_ID.reqTagName() );
	}
    
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public GetByIdReq newStore() {
		return new GetByIdReq( null, null );
	}
	
    @Override
    public GetByIdReq toStore( SnPoint src, GetByIdReq target ) {
    	super.toStore( src, target );
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitID ,target.unitID );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdReq src, SnPoint target ) {
    	super.toNode( src, target );
    	
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitID ,src.unitID	);

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
