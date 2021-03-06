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
public class FloatSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, FloatSorter < UDM >, Float > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public FloatSorter( Class<UDM> udmClass, SortOrder sortOrder, Var< ? > varObject ) {
		super( udmClass,sortOrder, null );
		this.fieldId.set( varObject.key() );
	}
	
	public FloatSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( udmClass, SortOrder.ASCENDING, unitFieldGetter );
	}

	public FloatSorter( Class<UDM> udmClass, SortOrder sortOrder,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		super( udmClass, sortOrder, unitFieldGetter );
		this.fieldId.set( getField() );
	}
	
	public Comparator < Float > newComparator() {
		return new Comparator < Float >() {
			@Override
			public int compare( Float o1, Float o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	@Override 
	public DefaultSorterTransformer < UDM,  FloatSorter < UDM >, Float > newTransformer() {  
		return new DefaultSorterTransformer<>() {
			@Override 
			public FloatSorter < UDM > newModel() { 
				return new FloatSorter<>( FloatSorter.this.udmClass,	SortOrder.ASCENDING, FloatSorter.this.fieldGetter );
			}
		}; 
	}
	
	
}
