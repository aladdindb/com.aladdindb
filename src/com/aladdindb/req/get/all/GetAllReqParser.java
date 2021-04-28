package com.aladdindb.req.get.all;

import com.aladdindb.Method;
import com.aladdindb.req.ReqParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetAllReqParser  extends ReqParser< GetAllReqModel >  {
	

    
    private enum ATR { blockSize };
    
    
	public GetAllReqParser() {
		super( Method.GET_ALL.req() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetAllReqModel newModel() {
		return new GetAllReqModel( null, 0 );
	}
	
    @Override
    public GetAllReqModel toModel( SnPoint src, GetAllReqModel target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asInt.get( ATR.blockSize, target.blockSize );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetAllReqModel src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asInt.set( ATR.blockSize, src.blockSize );

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
