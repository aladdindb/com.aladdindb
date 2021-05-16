package com.aladdindb.method.resp.add;

import com.aladdindb.method.Method;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddRespTransformer  extends Transformer < AddResp > {
	

    private enum ATR { unitID };
    
	
    //****************************************************************
    //
    //****************************************************************

    public AddRespTransformer() {
		super( Method.ADD.respTagName() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
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
