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
public class IntSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, IntSorter < UDM >, Integer > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public IntSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( SortOrder.ASCENDING, udmClass, unitFieldGetter );
	}

	public IntSorter( SortOrder sortOrder, Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		super( udmClass, sortOrder, unitFieldGetter );
		this.fieldId.set( getField() );
	}
	
	public Comparator < Integer > newComparator() {
		return new Comparator < Integer >() {
			@Override
			public int compare( Integer o1, Integer o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	@Override 
	public DefaultSorterTransformer < UDM,  IntSorter < UDM >, Integer > newTransformer() {  
		return new DefaultSorterTransformer<>() {
			@Override 
			public IntSorter < UDM > newModel() { 
				return new IntSorter<>( SortOrder.ASCENDING,	IntSorter.this.udmClass, IntSorter.this.fieldGetter );
			}
		}; 
	}
	
	
}
