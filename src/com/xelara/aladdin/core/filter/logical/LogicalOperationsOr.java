package com.xelara.aladdin.core.filter.logical;

import java.util.ArrayList;
import java.util.List;

import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.core.filter.FilterFactory;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;

public class LogicalOperationsOr < UDM extends DataModel< UDM > > 

		implements Filter < UDM, LogicalOperationsOr < UDM > > {  
	
	
    //****************************************************************
    //						Class-Attributes 
    //****************************************************************

	private final  FilterFactory < UDM > factory;
	
	
	public final List < Filter< UDM, ? extends DataModel< ? > > >  filterList = new ArrayList<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOperationsOr( FilterFactory< UDM > factory, Filter < UDM,  ? extends DataModel< ? > >... filterArray ) {
		
		this.factory = factory;
		
		for( var filter : filterArray ) {
			this.filterList.add( filter );
		}
		
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	public boolean prove( UDM model ) {
		boolean rv = false;
		
		var array = this.filterList.toArray( new Filter[ this.filterList.size() ] );
		
		int i = 0; do {
			rv = array[i++].prove( model );
		} while( i < array.length && !rv );
		
		return rv;
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( LogicalOperationsOr < UDM > model) {
		this.filterList.clear();
		model.filterList.forEach( this.filterList :: add );
	}

    //****************************************************************
    //
    //****************************************************************
	
	@Override
	public DataParser< LogicalOperationsOr < UDM > > createParser() {
		return new LogicalOperationsOrParser< UDM >( this.factory ); 
	}
	
	
}
