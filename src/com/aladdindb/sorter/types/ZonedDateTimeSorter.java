package com.aladdindb.sorter.types;

import java.time.ZonedDateTime;
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
public class ZonedDateTimeSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, ZonedDateTimeSorter < UDM >, ZonedDateTime > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public ZonedDateTimeSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		this( SortOrder.ASCENDING, udmClass, unitFieldGetter );
	}

	public ZonedDateTimeSorter( SortOrder sortOrder, Class<UDM> udmClass,  Function < Unit < UDM >, Var< ? > > unitFieldGetter  ) {
		super( sortOrder, udmClass, unitFieldGetter );
		this.fieldId.set( getField() );
	}
	
	public Comparator <ZonedDateTime> newComparator() {
		return new Comparator<ZonedDateTime>() {
			@Override
			public int compare( ZonedDateTime o1, ZonedDateTime o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	
	@Override 
	public DefaultSorterTransformer < UDM,  ZonedDateTimeSorter < UDM >, ZonedDateTime > newTransformer() {  
		return new DefaultSorterTransformer<>() { 
			@Override 
			public ZonedDateTimeSorter < UDM > newModel() { 
				return new ZonedDateTimeSorter<>( SortOrder.ASCENDING,	ZonedDateTimeSorter.this.udmClass, ZonedDateTimeSorter.this.fieldGetter );
			}
		};
	}
	
	
}
