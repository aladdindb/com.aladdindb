package com.aladdindb.filter.logical;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.filter.Filter;
import com.aladdindb.filter.FilterFactory;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.units.models.Unit;

public class LogicalOperationsAnd < UDM extends DataModel< UDM > >

		implements Filter < UDM, LogicalOperationsAnd < UDM > > {  
	
	
    //****************************************************************
    //						Class-Attributes 
    //****************************************************************

	private final  FilterFactory < UDM > factory;
	
	
	public final List < Filter< UDM, ? extends DataModel< ? > > >  filterList = new ArrayList<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOperationsAnd( FilterFactory< UDM > factory, Filter < UDM,  ? extends DataModel< ? > >... filterArray ) {
		
		this.factory = factory;
		
		for( var filter : filterArray ) {
			this.filterList.add( filter );
		}
		
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	@Override
	public boolean prove( Unit<UDM> model ) {
		boolean rv = true;
		var array = this.filterList.toArray( new Filter[ this.filterList.size() ] );
		
		int i = 0; do {
			rv = array[i++].prove( model ); 
		} while( i < array.length && rv );
		
		return rv;
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( LogicalOperationsAnd < UDM > model) {
		this.filterList.clear();
		model.filterList.forEach( this.filterList :: add );
	}

    //****************************************************************
    //
    //****************************************************************
	
	@Override
	public DataParser< LogicalOperationsAnd < UDM > > createParser() {
		return new LogicalOperationsAndParser< UDM >( this.factory ); 
	}
	
}
