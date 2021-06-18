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
public class DoubleSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, DoubleSorter < UDM >, Double > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public DoubleSorter( Class<UDM> udmClass, SortOrder sortOrder, Var< ? > varObject ) {
		super( udmClass, sortOrder, null );
		this.fieldId.set( varObject.key() );
	}
	
	public DoubleSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( udmClass, SortOrder.ASCENDING, unitFieldGetter );
	}

	public DoubleSorter( Class<UDM> udmClass, SortOrder sortOrder,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		super( udmClass, sortOrder, unitFieldGetter );
		this.fieldId.set( getField() );
	}
	
	public Comparator < Double > newComparator() {
		return new Comparator < Double >() {
			@Override
			public int compare( Double o1, Double o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	@Override 
	public DefaultSorterTransformer < UDM,  DoubleSorter < UDM >, Double > newTransformer() {  
		return new DefaultSorterTransformer<>() {
			@Override 
			public DoubleSorter < UDM > newModel() { 
				return new DoubleSorter<>( DoubleSorter.this.udmClass,	SortOrder.ASCENDING, DoubleSorter.this.fieldGetter );
			}
		}; 
	}
	
	
}
