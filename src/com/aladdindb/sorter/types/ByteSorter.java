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
public class ByteSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, ByteSorter < UDM >, Byte > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public ByteSorter( Class<UDM> udmClass, SortOrder sortOrder, Var< ? > varObject ) {
		super( udmClass, sortOrder, null );
		this.fieldId.set( varObject.key() );
	}
	
	public ByteSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( udmClass, SortOrder.ASCENDING, unitFieldGetter );
	}

	public ByteSorter( Class<UDM> udmClass, SortOrder sortOrder,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		super( udmClass, sortOrder, unitFieldGetter );
		this.fieldId.set( getField() );
	}
	
	public Comparator < Byte > newComparator() {
		return new Comparator < Byte >() {
			@Override
			public int compare( Byte o1, Byte o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	@Override 
	public DefaultSorterTransformer < UDM,  ByteSorter < UDM >, Byte > newTransformer() {  
		return new DefaultSorterTransformer<>() {
			@Override 
			public ByteSorter < UDM > newModel() { 
				return new ByteSorter<>( ByteSorter.this.udmClass,	SortOrder.ASCENDING, ByteSorter.this.fieldGetter );
			}
		}; 
	}
	
	
}
