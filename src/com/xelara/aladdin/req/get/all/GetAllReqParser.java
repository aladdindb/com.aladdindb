package com.xelara.aladdin.req.get.all;

import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetAllReqParser  extends ReqParser< GetAllReqModel >  {
	

    
    private enum ATR { blockSize };
    
    
	public GetAllReqParser() {
		super( Req.GET_ALL );
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
