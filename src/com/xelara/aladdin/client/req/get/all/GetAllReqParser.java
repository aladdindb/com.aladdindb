package com.xelara.aladdin.client.req.get.all;

import com.xelara.aladdin.client.req.Req;
import com.xelara.aladdin.core.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetAllReqParser extends DataParser < GetAllReq > {
	

    private enum ATR { unitGroupID, blockSize };
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetAllReqParser() {
		super( Req.GET_ALL );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetAllReq newModel() {
		return new GetAllReq();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public GetAllReq toModel( SnPoint src, GetAllReq target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID	,target.unitGroupID 	);
    	srcAtr.asInt.get( ATR.blockSize		,target.blockSize		);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetAllReq src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID  	,src.unitGroupID	);
    	targetAtr.asInt.set( ATR.blockSize  	,src.blockSize		);

        return target;
    }
    
    
    
}
