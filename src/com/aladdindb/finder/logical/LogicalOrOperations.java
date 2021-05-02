package com.aladdindb.finder.logical;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupplier;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.units.models.Unit;

public class LogicalOrOperations < UDM extends DataModel< UDM > > 

		implements Finder < UDM, LogicalOrOperations < UDM > > {  
	
	
    //****************************************************************
    //						Class-Attributes 
    //****************************************************************

	private final  FinderSupplier < UDM > factory;
	
	
	public final List < Finder< UDM, ? extends DataModel< ? > > >  filterList = new ArrayList<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOrOperations( FinderSupplier< UDM > factory, Finder < UDM,  ? extends DataModel< ? > >... filterArray ) {
		
		this.factory = factory;
		
		for( var filter : filterArray ) {
			this.filterList.add( filter );
		}
		
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	public boolean prove( Unit<UDM> model ) {
		boolean rv = false;
		
		var array = this.filterList.toArray( new Finder[ this.filterList.size() ] );
		
		int i = 0; do {
			rv = array[i++].prove( model );
		} while( i < array.length && !rv );
		
		return rv;
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( LogicalOrOperations < UDM > model) {
		this.filterList.clear();
		model.filterList.forEach( this.filterList :: add );
	}

    //****************************************************************
    //
    //****************************************************************
	
	@Override
	public DataTransformer< LogicalOrOperations < UDM > > createTransformer() {
		return new LogicalOrOperationsTransformer< UDM >( this.factory ); 
	}
	
	
}
