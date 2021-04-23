package com.xelara.aladdin.core.filter;

import java.util.ArrayList;
import java.util.List;

import com.xelara.structure.DataModel;
import com.xelara.structure.sn.SnPoint;

public class LogicalAndOperations < UDM extends DataModel< UDM > > extends ParserModelFilter< UDM,LogicalAndOperations< UDM >, Object>  { 
	
	
	public final List< ParserModelFilter< UDM, ? , ? >  > filterList = new ArrayList<>();
	
	private final  FilterFactory< UDM > factory; 
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalAndOperations( FilterFactory< UDM > factory, ParserModelFilter< UDM,?, ? >...filterArray ) {
		super( FilterFactoryDefault.FILTER_LOGICAL_AND );
		
		this.factory = factory;
		
		for( var filter : filterArray ) {
			this.filterList.add(filter);
		}
		
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	@Override
	public boolean prove( UDM model ) {
		boolean rv = true;
		
		var array = this.filterList.toArray( new ParserModelFilter[ this.filterList.size() ] );
		
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
		this.filterList.clear();
		model.filterList.forEach( this.filterList :: add );
	}

    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalAndOperations < UDM > newModel() {
		return new LogicalAndOperations< UDM >( this.factory );
	}

	@Override
	public LogicalAndOperations < UDM > toModel( SnPoint src, LogicalAndOperations < UDM > target ) {
		
		src.children.forEach( node -> {
			this.factory.createFilter( node, target.filterList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalAndOperations < UDM > src, SnPoint target ) {
		
		var array = src.filterList.toArray( new ParserModelFilter[ src.filterList.size() ] );
		 
		for( var filter : array ) {
			var node = filter.toNode( filter );
			target.children.add( node );  
		}
		
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	
}
