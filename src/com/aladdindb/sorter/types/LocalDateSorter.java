package com.aladdindb.sorter.types;

import java.time.LocalDate;
import java.util.Comparator;

import com.aladdindb.sorter.DefaultSorter;
import com.aladdindb.sorter.SortOrder;
import com.aladdindb.structure.DataModel;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class LocalDateSorter  <

	UDM 			extends DataModel		< UDM >, 
	SORTER_MODEL	extends DefaultSorter	< UDM, SORTER_MODEL, LocalDate >

> extends DefaultSorter < UDM, SORTER_MODEL, LocalDate > {

	
	public LocalDateSorter( SortOrder sortOrder ) {
		super( sortOrder ); 
	}
	
	public Comparator <LocalDate> newComparator() {
		return new Comparator<LocalDate>() {
			@Override
			public int compare( LocalDate o1, LocalDate o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	
	
}
