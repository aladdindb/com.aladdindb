package com.aladdindb.finder.logical;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.FinderSupport;
import com.aladdindb.finder.Finder;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;

public class LogicalOrFinders < UDM extends DataModel< UDM > > 

		implements Finder < UDM, LogicalOrFinders < UDM > > {  
	
	
    //****************************************************************
    //						Class-Attributes 
    //****************************************************************

	private final  FinderSupport< UDM > finderSupport;
	
	
	public final List < Finder< UDM, ? extends DataModel< ? > > >  finderList = new ArrayList<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOrFinders( FinderSupport< UDM > finderSupport ) {
		this.finderSupport = finderSupport;
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
	
	public boolean prove( Unit<UDM> unit ) {
		boolean rv = false;
		
		var finders = this.finderList.toArray( new Finder[ this.finderList.size() ] );
		
		int i = 0; do {
			rv = finders[i++].prove( unit );
		} while( i < finders.length && !rv );
		
		return rv;
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( LogicalOrFinders < UDM > model) {
		this.finderList.clear();
		model.finderList.forEach( this.finderList :: add );
	}

    //****************************************************************
    //
    //****************************************************************
	
	@Override
	public Transformer< LogicalOrFinders < UDM > > newTransformer() {
		return new LogicalOrFindersTransformer< UDM >( this.finderSupport ); 
	}
	
}
