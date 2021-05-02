package com.aladdindb.finder;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 */
public abstract class DefaultFinderTransformer <

	UDM 	extends DataModel		< UDM >,
	MODEL	extends DefaultFinder	< UDM, MODEL, ? >

> extends DataTransformer< MODEL > {
	
	
	public enum ATR { operator, pattern }

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DefaultFinderTransformer( String tagKey ) {
		super( tagKey );
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public MODEL toModel( SnPoint src, MODEL target ) {
		var srcAtr = new SnAttributeAccess(src);
		srcAtr.asStr.get( ATR.operator	,target.operator );
		srcAtr.asStr.get( ATR.pattern	,target.pattern );
		return target;
	}

	@Override
	public SnPoint toNode( MODEL src, SnPoint target ) {
		var srcAtr = new SnAttributeAccess(target);
		srcAtr.asStr.set( ATR.operator	,src.operator 	);
		srcAtr.asStr.set( ATR.pattern	,src.pattern 	);
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
}
