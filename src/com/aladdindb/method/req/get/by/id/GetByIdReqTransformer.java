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
	

    
    private enum ATR { unitId };
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdReqTransformer() {
		super( Method.getById.store() );
	}
    
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public GetByIdReq newModel() {
		return new GetByIdReq( null, null );
	}
	
    @Override
    public GetByIdReq toModel( SnPoint src, GetByIdReq target ) {
    	super.toModel( src, target );
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitId ,target.unitId );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdReq src, SnPoint target ) {
    	super.toNode( src, target );
    	
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitId ,src.unitId	);

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
