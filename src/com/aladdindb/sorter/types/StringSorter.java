package com.aladdindb.sorter.types;

import java.util.Comparator;

import com.aladdindb.Genie;
import com.aladdindb.sorter.DefaultSorter;
import com.aladdindb.sorter.SortOrder;
import com.aladdindb.structure.DataModel;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class StringSorter  < UDM extends DataModel	< UDM > > extends DefaultSorter< UDM, String > {

	
	public StringSorter( SortOrder sortOrder ) {
		super( sortOrder );
	}
	
	public Comparator<String> createComparator() {
		return new Comparator<String>() {
			@Override
			public int compare( String o1, String o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	
}
