package com.aladdindb.sorter;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract  class DefaultSorterTransformer <

	UDM 			extends DataModel		< UDM >,
	SORTER_MODEL	extends DefaultSorter	< UDM, SORTER_MODEL, VT >,
	VT

> extends Transformer< SORTER_MODEL > {
	
	
	public enum ATR { sortOrder }

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DefaultSorterTransformer( String tagKey ) {
		super( tagKey );
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public SORTER_MODEL toModel( SnPoint src, SORTER_MODEL target ) {
		var srcAtr = new SnAttributeAccess(src);
		srcAtr.asStr.get( ATR.sortOrder	,target.sortOrderVar );
		return target;
	}

	@Override
	public SnPoint toNode( SORTER_MODEL src, SnPoint target ) {
		var srcAtr = new SnAttributeAccess(target);
		srcAtr.asStr.set( ATR.sortOrder	,src.sortOrderVar 	);
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
}
