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
	
	
	public enum ATR { operator, pattern, fieldId }

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DefaultFinderTransformer() {
		super( Finder.NAME );
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public FINDER_MODEL toModel( SnPoint src, FINDER_MODEL target ) {
		var srcAtr = new SnAttributeAccess(src);
		srcAtr.asStr.get( ATR.operator	,target.operator );
		srcAtr.asStr.get( ATR.pattern	,target.pattern );
		srcAtr.asStr.get( ATR.fieldId	,target.fieldId );
		return target;
	}

	@Override
	public SnPoint toNode( FINDER_MODEL src, SnPoint target ) {
		var srcAtr = new SnAttributeAccess(target);
		srcAtr.asStr.set( ATR.operator	,src.operator 	);
		srcAtr.asStr.set( ATR.pattern	,src.pattern 	);
		srcAtr.asStr.set( ATR.fieldId	,src.fieldId 		);
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	public static final FinderAtr newFinderAtr( SnPoint src ) {
		var rv = new FinderAtr();
		
		var srcAtr = new SnAttributeAccess(src);
		
		srcAtr.asStr.get( ATR.operator	,str -> rv.operator = str );
		srcAtr.asStr.get( ATR.pattern	,str -> rv.pattern 	= str );
		srcAtr.asStr.get( ATR.fieldId	,str -> rv.fieldId 	= str );
		
		return rv;
	}
	
	public static class FinderAtr {
		public String operator;
		public String pattern;
		public String fieldId;
	}
}
