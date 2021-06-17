package com.aladdindb.sorter.types;

import java.util.Comparator;
import java.util.function.Function;

import com.aladdindb.sorter.DefaultSorter;
import com.aladdindb.sorter.DefaultSorterTransformer;
import com.aladdindb.sorter.SortOrder;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public class BooleanSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, BooleanSorter < UDM >, Boolean > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public BooleanSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( SortOrder.ASCENDING, udmClass, unitFieldGetter );
	}

	public BooleanSorter( SortOrder sortOrder, Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		super( udmClass, sortOrder, unitFieldGetter );
		this.fieldId.set( getField() );
	}
	
	public Comparator < Boolean > newComparator() {
		return new Comparator < Boolean >() {
			@Override
			public int compare( Boolean o1, Boolean o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	@Override 
	public DefaultSorterTransformer < UDM,  BooleanSorter < UDM >, Boolean > newTransformer() {  
		return new DefaultSorterTransformer<>() {
			@Override  
			public BooleanSorter < UDM > newModel() { 
				return new BooleanSorter<>( SortOrder.ASCENDING,	BooleanSorter.this.udmClass, BooleanSorter.this.fieldGetter );
			}
		}; 
	}
	
	
}
