package com.aladdindb.method.resp.get.block;

import com.aladdindb.method.Method;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

public class BlockNaviRespTransformer extends Transformer< BlockNavResp > {
	
    private enum ATR { methodSesionID, hasLeft, hasRight, unitsIdBlock };
	
	
	public BlockNaviRespTransformer() {
		super( Method.getBlock.respTagName() );
	}

	
	@Override
	public BlockNavResp newModel() {
		return new BlockNavResp();
	}

	@Override
	public BlockNavResp toModel(SnPoint src, BlockNavResp target) {
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.methodSesionID	,target.methodSessionID 	);
    	
    	srcAtr.asBool	.get( ATR.hasLeft			,target.hasLeft				);
    	srcAtr.asBool	.get( ATR.hasRight			,target.hasRight			);

    	src.value.get( target.unitsIdBlock );

        return target;
	}

	@Override
	public SnPoint toNode( BlockNavResp src, SnPoint target ) {
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr	.set( ATR.methodSesionID  	,src.methodSessionID	);
    	
    	targetAtr.asBool.set( ATR.hasLeft  			,src.hasLeft		);
    	targetAtr.asBool.set( ATR.hasRight  		,src.hasRight		);

    	target.value.set( src.unitsIdBlock );
    	
        return target;
	}

	
}
