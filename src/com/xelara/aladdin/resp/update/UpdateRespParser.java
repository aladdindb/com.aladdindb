package com.xelara.aladdin.resp.update;

import com.xelara.aladdin.req.Cmd;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UpdateRespParser  extends DataParser < UpdateResp > {
	

    private enum ATR { unitID };
    
	
    //****************************************************************
    //
    //****************************************************************

    public UpdateRespParser() {
		super( Cmd.UPDATE.res() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public UpdateResp newModel() {
		return new UpdateResp();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public UpdateResp toModel( SnPoint src, UpdateResp target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitID, target.unitID     	);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( UpdateResp src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitID, src.unitID 	);
    	
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
