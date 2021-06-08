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
public class StringSorter < UDM	extends DataModel < UDM > > extends DefaultSorter < UDM, StringSorter < UDM >, String > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public StringSorter( Class<UDM> udmClass,  Function < Unit < UDM >, Var< String > > unitFieldGetter  ) {
		this( SortOrder.ASCENDING, udmClass, unitFieldGetter );
	}

	public StringSorter( SortOrder sortOrder, Class<UDM> udmClass,  Function < Unit < UDM >, Var< String > > unitFieldGetter  ) {
		super( sortOrder, udmClass, unitFieldGetter );
	}
	
	public Comparator < String > newComparator() {
		return new Comparator < String >() {
			@Override
			public int compare( String o1, String o2 ) {
				return (o1 != null && o2 != null) ? o1.compareTo(o2) : 0;
			}
 		};
	}
	 
	@Override 
	public DefaultSorterTransformer < UDM,  StringSorter < UDM >, String > newTransformer() {  
		return new DefaultSorterTransformer<>() {
			@Override 
			public StringSorter < UDM > newModel() { 
				return new StringSorter<>( SortOrder.ASCENDING,	StringSorter.this.udmClass, StringSorter.this.fieldGetter );
			}
		};
	}
	
	
}
