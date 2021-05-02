package com.aladdindb.finder.logical;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupplier;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.units.models.Unit;

public class LogicalAndOperations < UDM extends DataModel< UDM > >

		implements Finder < UDM, LogicalAndOperations < UDM > > {  
	
	
    //****************************************************************
    //						Class-Attributes 
    //****************************************************************

	private final  FinderSupplier < UDM > finderSupplier;
	
	
	public final List < Finder< UDM, ? extends DataModel< ? > > >  finderList = new ArrayList<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalAndOperations( FinderSupplier< UDM > finderSupplier, Finder < UDM,  ? extends DataModel< ? > >... finderArray ) {
		
		this.finderSupplier = finderSupplier;
		
		for( var filter : finderArray ) {
			this.finderList.add( filter );
		}
		
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	@Override
	public boolean prove( Unit<UDM> model ) {
		boolean rv = true;
		var array = this.finderList.toArray( new Finder[ this.finderList.size() ] );
		
		int i = 0; do {
			rv = array[i++].prove( model ); 
		} while( i < array.length && rv );
		
		return rv;
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( LogicalAndOperations < UDM > model) {
		this.finderList.clear();
		model.finderList.forEach( this.finderList :: add );
	}

    //****************************************************************
    //
    //****************************************************************
	
	@Override
	public DataTransformer< LogicalAndOperations < UDM > > createTransformer() {
		return new LogicalAndOperationsTransformer< UDM >( this.finderSupplier ); 
	}
	
}
