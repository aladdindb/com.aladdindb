package com.aladdindb.method.resp.update;

import com.aladdindb.method.Method;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UpdateRespTransformer  extends DataTransformer < UpdateResp > {
	

    private enum ATR { unitID };
    
	
    //****************************************************************
    //
    //****************************************************************

    public UpdateRespTransformer() {
		super( Method.UPDATE.respTagName() );
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
