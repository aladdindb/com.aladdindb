package com.xelara.aladdin.req.get.filtered;

import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.core.filter.FilterFactory;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqParser;
import com.xelara.structure.DataModel;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetFilteredReqParser <

	UDM 			extends DataModel	< UDM >,
	FILTER_MODEL 	extends Filter		< UDM, FILTER_MODEL>

>  extends ReqParser< GetFilteredReqModel < UDM, FILTER_MODEL > >  {
	

    private enum ATR { blockSize };
    
    private final FilterFactory< UDM > filterFac; 
    
	public GetFilteredReqParser( FilterFactory< UDM > filterFac ) {
		super( Req.GET_BY_FILTER );
		this.filterFac = filterFac;
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetFilteredReqModel< UDM, FILTER_MODEL > newModel() {
		return new GetFilteredReqModel< UDM, FILTER_MODEL >( null, 0, null );
	}
	
    @Override
    public GetFilteredReqModel< UDM, FILTER_MODEL > toModel( SnPoint src, GetFilteredReqModel< UDM , FILTER_MODEL> target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asInt.get( ATR.blockSize, target.blockSize );
    	
    	src.children.snBottom.get( filterNode -> {
    		this.filterFac.createFilter( filterNode, filterModel -> {
    			target.filter.set( (FILTER_MODEL) filterModel );
    		});
    	});
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetFilteredReqModel< UDM, FILTER_MODEL > src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asInt.set( ATR.blockSize, src.blockSize );
    	
    	src.filter.get( filter -> filter.createParser().toNode( filter, target.children :: add ) );

    	
        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
