package com.xelara.aladdin.resp.add;

import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.resp.Resp;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddRespParser  extends DataParser < AddResp > {
	

    private enum ATR { unitID };
    
	
    //****************************************************************
    //
    //****************************************************************

    public AddRespParser() {
		super( Resp.ADD );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public AddResp newModel() {
		return new AddResp();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public AddResp toModel( SnPoint src, AddResp target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.unitID, target.unitID     	);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddResp src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr	.set( ATR.unitID, src.unitID 	);
    	
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
