package com.aladdindb.sorter.types;

import java.time.LocalDate;
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
public class DateSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, DateSorter < UDM >, LocalDate > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public DateSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		this( SortOrder.ASCENDING, udmClass, unitFieldGetter );
	}

	public DateSorter( SortOrder sortOrder, Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		super( udmClass, sortOrder, unitFieldGetter );
		this.fieldId.set( getField() );
	}
	
	public Comparator <LocalDate> newComparator() {
		return new Comparator<LocalDate>() {
			@Override
			public int compare( LocalDate o1, LocalDate o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	
	@Override 
	public DefaultSorterTransformer < UDM,  DateSorter < UDM >, LocalDate > newTransformer() {  
		return new DefaultSorterTransformer<>() { 
			@Override 
			public DateSorter < UDM > newModel() { 
				return new DateSorter<>( SortOrder.ASCENDING,	DateSorter.this.udmClass, DateSorter.this.fieldGetter );
			}
		};
	}
	
	
}
