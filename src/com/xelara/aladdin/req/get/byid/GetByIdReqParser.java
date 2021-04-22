package com.xelara.aladdin.req.get.byid;

import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByIdReqParser  extends ReqParser< GetByIdReqModel >  {
	

    
    private enum ATR { unitID };
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdReqParser() {
		super( Req.GET_BY_ID );
	}
    
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public GetByIdReqModel newModel() {
		return new GetByIdReqModel( null, null );
	}
	
    @Override
    public GetByIdReqModel toModel( SnPoint src, GetByIdReqModel target ) {
    	super.toModel( src, target );
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitID ,target.unitID );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdReqModel src, SnPoint target ) {
    	super.toNode( src, target );
    	
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitID ,src.unitID	);

        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
