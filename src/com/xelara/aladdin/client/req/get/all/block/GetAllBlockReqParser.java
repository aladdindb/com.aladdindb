package com.xelara.aladdin.client.req.get.all.block;

import com.xelara.aladdin.client.req.Req;
import com.xelara.aladdin.core.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetAllBlockReqParser extends DataParser < GetAllBlockReq > {
	

    private enum ATR { unitGroupID, cmdSessionID, direction };
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetAllBlockReqParser() {
		super( Req.GET_ALL_BLOCK );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetAllBlockReq newModel() {
		return new GetAllBlockReq();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public GetAllBlockReq toModel( SnPoint src, GetAllBlockReq target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID	,target.unitGroupID 	);
    	srcAtr.asStr.get( ATR.cmdSessionID	,target.cmdSessionID 	);
    	srcAtr.asStr.get( ATR.direction		,target.direction		);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetAllBlockReq src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID  	,src.unitGroupID	);
    	targetAtr.asStr.set( ATR.cmdSessionID  	,src.cmdSessionID	);
    	targetAtr.asStr.set( ATR.direction  	,src.direction		);

        return target;
    }
    
    
    
}
