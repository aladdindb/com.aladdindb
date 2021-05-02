package com.aladdindb.method.resp.get.block;

import com.aladdindb.method.Method;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

public class BlockRespTransformer extends DataTransformer< BlockResp > {
	
    private enum ATR { cmdSesionID, hasLeft, hasRight, unitsIdBlock };
	
	
	public BlockRespTransformer() {
		super( Method.GET_ALL_BLOCK.respTagName() );
	}

	
	@Override
	public BlockResp newModel() {
		return new BlockResp();
	}

	@Override
	public BlockResp toModel(SnPoint src, BlockResp target) {
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.cmdSesionID	,target.cmdSessionID 	);
    	
    	srcAtr.asBool	.get( ATR.hasLeft		,target.hasLeft			);
    	srcAtr.asBool	.get( ATR.hasRight		,target.hasRight		);

    	src.value.get( target.unitsIdBlock );

        return target;
	}

	@Override
	public SnPoint toNode( BlockResp src, SnPoint target ) {
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr	.set( ATR.cmdSesionID  	,src.cmdSessionID	);
    	
    	targetAtr.asBool.set( ATR.hasLeft  		,src.hasLeft		);
    	targetAtr.asBool.set( ATR.hasRight  	,src.hasRight		);

    	target.value.set( src.unitsIdBlock );
    	
        return target;
	}

	
}
