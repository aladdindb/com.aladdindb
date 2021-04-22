package com.xelara.aladdin.resp.get.all.block;

import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.resp.Resp;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

public class GetAllBlockRespParser extends DataParser< GetAllBlockResp > {
	
    private enum ATR { cmdSesionID, hasLeft, hasRight, unitsIdBlock };
	
	
	public GetAllBlockRespParser() {
		super( Resp.GET_ALL_BLOCK );
	}

	
	@Override
	public GetAllBlockResp newModel() {
		return new GetAllBlockResp();
	}

	@Override
	public GetAllBlockResp toModel(SnPoint src, GetAllBlockResp target) {
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.cmdSesionID	,target.cmdSessionID 	);
    	
    	srcAtr.asBool	.get( ATR.hasLeft		,target.hasLeft			);
    	srcAtr.asBool	.get( ATR.hasRight		,target.hasRight		);

    	src.value.get( target.unitsIdBlock );

        return target;
	}

	@Override
	public SnPoint toNode( GetAllBlockResp src, SnPoint target ) {
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr	.set( ATR.cmdSesionID  	,src.cmdSessionID	);
    	
    	targetAtr.asBool.set( ATR.hasLeft  		,src.hasLeft		);
    	targetAtr.asBool.set( ATR.hasRight  	,src.hasRight		);

    	target.value.set( src.unitsIdBlock );
    	
        return target;
	}

	
}
