package com.aladdindb.sorter.types;

import java.util.Comparator;

import com.aladdindb.sorter.DefaultSorter;
import com.aladdindb.sorter.SortOrder;
import com.aladdindb.structure.Store;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class StringSorter  <

	UDM 			extends Store			< UDM >, 
	SORTER_MODEL	extends DefaultSorter	< UDM, SORTER_MODEL, String >

> extends DefaultSorter< UDM, SORTER_MODEL, String > {

	
	public StringSorter( SortOrder sortOrder ) {
		super( sortOrder );
	}
	
	public Comparator < String > newComparator() {
		return new Comparator < String >() {
			@Override
			public int compare( String o1, String o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	
}
