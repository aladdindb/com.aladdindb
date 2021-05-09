package com.aladdindb.sorter.types;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.aladdindb.Genie;
import com.aladdindb.sorter.DefaultSorter;
import com.aladdindb.sorter.SortOrder;
import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.IntIndex;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class LocalDateSorter  < UDM extends DataModel	< UDM > > extends DefaultSorter< UDM, LocalDate > {

	
	public LocalDateSorter( SortOrder sortOrder ) {
		super( sortOrder );
	}
	
	public Comparator<LocalDate> createComparator() {
		return new Comparator<LocalDate>() {
			@Override
			public int compare( LocalDate o1, LocalDate o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	
	
}
