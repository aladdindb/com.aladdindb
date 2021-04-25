package com.xelara.aladdin.resp.add;

import com.xelara.aladdin.resp.Resp;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

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
		super( Resp.ADD );
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
