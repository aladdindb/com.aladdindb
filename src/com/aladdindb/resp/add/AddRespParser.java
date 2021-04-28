package com.aladdindb.resp.add;

import com.aladdindb.Method;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddRespParser  extends DataParser < AddRespModel > {
	

    private enum ATR { unitID };
    
	
    //****************************************************************
    //
    //****************************************************************

    public AddRespParser() {
		super( Method.ADD.res() );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public AddRespModel newModel() {
		return new AddRespModel();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public AddRespModel toModel( SnPoint src, AddRespModel target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.unitID, target.unitID     	);
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddRespModel src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr	.set( ATR.unitID, src.unitID 	);
    	
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
