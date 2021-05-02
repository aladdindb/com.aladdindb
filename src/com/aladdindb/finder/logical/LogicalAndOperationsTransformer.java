package com.aladdindb.finder.logical;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinnderType;
import com.aladdindb.finder.FinderSupplier;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;


public class LogicalAndOperationsTransformer < UDM extends DataModel< UDM > > extends DataTransformer< LogicalAndOperations < UDM > > {

	
	private final  FinderSupplier < UDM > finderSupplier; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalAndOperationsTransformer( FinderSupplier < UDM > finderSupplier ) { 
		
		super( FinnderType.LOGICAL_AND.tagName() );
		
		this.finderSupplier = finderSupplier;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalAndOperations < UDM > newModel() {
		return new LogicalAndOperations< UDM >( this.finderSupplier );
	}

	@Override
	public LogicalAndOperations < UDM > toModel( SnPoint src, LogicalAndOperations < UDM > target ) {
		
		src.children.forEach( node -> {
			this.finderSupplier.createFinder( node, target.finderList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalAndOperations < UDM > src, SnPoint target ) {

		var array = src.finderList.toArray( new Finder[ src.finderList.size() ] );
//		 
		for( var finder : array ) {
			var node = finder.createTransformer().toNode( finder );
			target.children.add( node );  
		}
//		
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	
}
