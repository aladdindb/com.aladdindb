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
public class LocalDateSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, LocalDateSorter < UDM >, LocalDate > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LocalDateSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< LocalDate > > unitFieldGetter  ) {
		this( SortOrder.ASCENDING, udmClass, unitFieldGetter );
	}

	public LocalDateSorter( SortOrder sortOrder, Class<UDM> udmClass,  Function < Unit < UDM >, Var< LocalDate > > unitFieldGetter  ) {
		super( sortOrder, udmClass, unitFieldGetter );
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
	public DefaultSorterTransformer < UDM,  LocalDateSorter < UDM >, LocalDate > newTransformer() {  
		return new DefaultSorterTransformer<>() { 
			@Override 
			public LocalDateSorter < UDM > newModel() { 
				return new LocalDateSorter<>( SortOrder.ASCENDING,	LocalDateSorter.this.udmClass, LocalDateSorter.this.fieldGetter );
			}
		};
	}
	
	
}
