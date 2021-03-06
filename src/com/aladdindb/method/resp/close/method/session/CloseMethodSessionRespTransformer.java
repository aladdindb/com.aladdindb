package com.aladdindb.method.resp.close.method.session;

import com.aladdindb.method.Method;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

public class CloseMethodSessionRespTransformer extends Transformer< CloseMethodSessionResp > {
	
    private enum ATR { successful };
	
	
	public CloseMethodSessionRespTransformer() {
		super( Method.closeSession.respTagName() );
	}

	
	@Override
	public CloseMethodSessionResp newModel() {
		return new CloseMethodSessionResp();
	}

	@Override
	public CloseMethodSessionResp toModel(SnPoint src, CloseMethodSessionResp target) {
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asBool	.get( ATR.successful			,target.successful			);

        return target;
	}

	@Override
	public SnPoint toNode( CloseMethodSessionResp src, SnPoint target ) {
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asBool.set( ATR.successful  			,src.successful		);
    	
        return target;
	}

	
}
