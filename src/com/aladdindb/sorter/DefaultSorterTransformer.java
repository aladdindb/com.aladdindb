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
	
	
	public enum ATR { sortOrder, fieldId }

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DefaultSorterTransformer() {
		super( Sorter.NAME );
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public SORTER_MODEL toModel( SnPoint src, SORTER_MODEL target ) {
		var srcAtr = new SnAttributeAccess(src);
		srcAtr.asStr.get( ATR.sortOrder	,target.sortOrderVar );
		srcAtr.asStr.get( ATR.fieldId	,target.fieldId );
		return target;
	}

	@Override
	public SnPoint toNode( SORTER_MODEL src, SnPoint target ) {
		var srcAtr = new SnAttributeAccess(target);
		srcAtr.asStr.set( ATR.sortOrder		,src.sortOrderVar 	);
		srcAtr.asStr.set( ATR.fieldId		,src.fieldId 		);
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	public static final SorterAtr newSorterAtr( SnPoint src ) {
		var rv = new SorterAtr();
		
		var srcAtr = new SnAttributeAccess(src);
		
		srcAtr.asStr.get( ATR.sortOrder		,str -> rv.sortOrder 	= SortOrder.valueOf( str ) );
		srcAtr.asStr.get( ATR.fieldId		,str -> rv.fieldId 		= str );
		
		return rv;
	}
	
	public static class SorterAtr {
		public SortOrder 	sortOrder;
		public String 		fieldId;
	}
	
}
