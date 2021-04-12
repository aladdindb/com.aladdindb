package com.xelara.aladdin.client.req.get.byid;

import com.xelara.aladdin.client.req.Req;
import com.xelara.aladdin.core.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByIdReqParser extends DataParser < GetByIdReq > {
	

    private enum ATR { unitGroupID, unitID };
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdReqParser() {
		super( Req.GET_BY_ID );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdReq newModel() {
		return new GetByIdReq();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public GetByIdReq toModel( SnPoint src, GetByIdReq target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID	,target.unitGroupID 	);
    	srcAtr.asStr.get( ATR.unitID		,target.unitID 			);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdReq src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID  	,src.unitGroupID	);
    	targetAtr.asStr.set( ATR.unitID  		,src.unitID			);

        return target;
    }
    
    
    
}
