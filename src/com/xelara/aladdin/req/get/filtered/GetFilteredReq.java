package com.xelara.aladdin.req.get.filtered;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqProcess;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockResp;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockRespParser;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

public class GetFilteredReq < UDM extends DataModel < UDM > >
			extends ReqProcess< GetAllBlockResp, UDM, GetFilteredReq< UDM > >  {

	
    private enum ATR { blockSize };
	
    
	public final Var< Integer > blockSize = new Var<>();


	public GetFilteredReq() {
		this( null, null );
	}
	
	public GetFilteredReq( Integer blockSize, UnitsChannel< UDM > unitsChanel ) {
		super( Req.GET_ALL_FILTERED, null, unitsChanel );
		
		this.blockSize.set( blockSize );
	}

    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( GetFilteredReq < UDM > model ) {
		super.fill( model );
		
		this.blockSize.set( model.blockSize );
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

    public GetFilteredReq< UDM > newModel() {
		return new GetFilteredReq< UDM >();
	}
	
    @Override
    public GetFilteredReq< UDM > toModel( SnPoint src, GetFilteredReq< UDM > target ) {
    	super.toModel( src, target );
    	
    	var srcAtr = new SnAttributeAccess( src );
    	
    	srcAtr.asInt.get( ATR.blockSize, target.blockSize );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetFilteredReq< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
    	
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asInt.set( ATR.blockSize ,src.blockSize );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
	

}
