package com.aladdindb.finder.logical;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupport;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.units.models.Unit;

public class LogicalOrOperations < UDM extends Store< UDM > > 

		implements Finder < UDM, LogicalOrOperations < UDM > > {  
	
	
    //****************************************************************
    //						Class-Attributes 
    //****************************************************************

	private final  FinderSupport < UDM > finderSupplier;
	
	
	public final List < Finder< UDM, ? extends Store< ? > > >  finderList = new ArrayList<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOrOperations( FinderSupport< UDM > finderSupplier ) {
		this.finderSupplier = finderSupplier;
	}

	
	public void addFinder( Finder < UDM,  ? extends Store< ? > >... finders ) {
		for( var finder : finders )  this.finderList.add( finder );
	}

	public void addFinder( Finder < UDM,  ? extends Store< ? > > finder ) {
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
	public Transformer< LogicalOrOperations < UDM > > newTransformer() {
		return new LogicalOrOperationsTransformer< UDM >( this.finderSupplier ); 
	}
	
	
}
