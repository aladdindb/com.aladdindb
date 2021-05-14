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

	private final  FinderSupplier < UDM > finderSupplier;
	
	
	public final List < Finder< UDM, ? extends DataModel< ? > > >  finderList = new ArrayList<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOrOperations( FinderSupplier< UDM > finderSupplier ) {
		this.finderSupplier = finderSupplier;
	}

	
	public void addFinder( Finder < UDM,  ? extends DataModel< ? > >... finders ) {
		for( var finder : finders )  this.finderList.add( finder );
	}

	public void addFinder( Finder < UDM,  ? extends DataModel< ? > > finder ) {
		this.finderList.add(finder);
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	public boolean prove( Unit<UDM> model ) {
		boolean rv = false;
		
		var array = this.finderList.toArray( new Finder[ this.finderList.size() ] );
		
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
		this.finderList.clear();
		model.finderList.forEach( this.finderList :: add );
	}

    //****************************************************************
    //
    //****************************************************************
	
	@Override
	public DataTransformer< LogicalOrOperations < UDM > > newTransformer() {
		return new LogicalOrOperationsTransformer< UDM >( this.finderSupplier ); 
	}
	
	
}
