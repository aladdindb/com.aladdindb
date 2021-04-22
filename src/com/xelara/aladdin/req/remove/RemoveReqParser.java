package com.xelara.aladdin.req.remove;

import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class RemoveReqParser  extends ReqParser< RemoveReqModel >  {
	

    
    private enum ATR { unitID };
    
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveReqParser() {
		super( Req.REMOVE );
	}
    
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public RemoveReqModel newModel() {
		return new RemoveReqModel( null, null );
	}
	
    @Override
    public RemoveReqModel toModel( SnPoint src, RemoveReqModel target ) {
    	super.toModel( src, target );
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitID ,target.unitID );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( RemoveReqModel src, SnPoint target ) {
    	super.toNode( src, target );
    	
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitID ,src.unitID	);

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
