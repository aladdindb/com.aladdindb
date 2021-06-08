package com.aladdindb.finder;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class DefaultFinderTransformer <

	UDM 			extends DataModel		< UDM >,
	FINDER_MODEL	extends DefaultFinder	< UDM, FINDER_MODEL, VT >,
	VT

> extends Transformer< FINDER_MODEL > {
	
	
	public enum ATR { operator, pattern, field }

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DefaultFinderTransformer( String tagKey ) {
		super( "Finder" );
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public FINDER_MODEL toModel( SnPoint src, FINDER_MODEL target ) {
		var srcAtr = new SnAttributeAccess(src);
		srcAtr.asStr.get( ATR.operator	,target.operator );
		srcAtr.asStr.get( ATR.pattern	,target.pattern );
		srcAtr.asStr.get( ATR.field		,target.field );
		return target;
	}

	@Override
	public SnPoint toNode( FINDER_MODEL src, SnPoint target ) {
		var srcAtr = new SnAttributeAccess(target);
		srcAtr.asStr.set( ATR.operator	,src.operator 	);
		srcAtr.asStr.set( ATR.pattern	,src.pattern 	);
		srcAtr.asStr.set( ATR.field		,src.field 		);
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
}
