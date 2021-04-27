package com.xelara.aladdin.resp.get.block;

import com.xelara.aladdin.req.Cmd;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

public class GetBlockRespParser extends DataParser< GetBlockRespModel > {
	
    private enum ATR { cmdSesionID, hasLeft, hasRight, unitsIdBlock };
	
	
	public GetBlockRespParser() {
		super( Cmd.GET_ALL_BLOCK.res() );
	}

	
	@Override
	public GetBlockRespModel newModel() {
		return new GetBlockRespModel();
	}

	@Override
	public GetBlockRespModel toModel(SnPoint src, GetBlockRespModel target) {
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.cmdSesionID	,target.cmdSessionID 	);
    	
    	srcAtr.asBool	.get( ATR.hasLeft		,target.hasLeft			);
    	srcAtr.asBool	.get( ATR.hasRight		,target.hasRight		);

    	src.value.get( target.unitsIdBlock );

        return target;
	}

	@Override
	public SnPoint toNode( GetBlockRespModel src, SnPoint target ) {
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr	.set( ATR.cmdSesionID  	,src.cmdSessionID	);
    	
    	targetAtr.asBool.set( ATR.hasLeft  		,src.hasLeft		);
    	targetAtr.asBool.set( ATR.hasRight  	,src.hasRight		);

    	target.value.set( src.unitsIdBlock );
    	
        return target;
	}

	
}
