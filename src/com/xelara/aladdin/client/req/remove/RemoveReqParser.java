package com.xelara.aladdin.client.req.remove;

import com.xelara.aladdin.client.req.Req;
import com.xelara.aladdin.core.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class RemoveReqParser extends DataParser < RemoveReq > {
	

    private enum ATR { unitGroupID, unitID };
    
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveReqParser() {
		super( Req.REMOVE );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveReq newModel() {
		return new RemoveReq();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public RemoveReq toModel( SnPoint src, RemoveReq target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID	,target.unitGroupID 	);
    	srcAtr.asStr.get( ATR.unitID		,target.unitID 			);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( RemoveReq src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID  	,src.unitGroupID	);
    	targetAtr.asStr.set( ATR.unitID  		,src.unitID			);

        return target;
    }
    
    
    
}
