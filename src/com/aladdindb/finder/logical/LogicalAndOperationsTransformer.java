package com.aladdindb.finder.logical;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.Type;
import com.aladdindb.finder.FinderSupport;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public class LogicalAndOperationsTransformer < UDM extends Store< UDM > > extends Transformer< LogicalAndOperations < UDM > > {

	
	private final  FinderSupport < UDM > finderSupplier; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalAndOperationsTransformer( FinderSupport < UDM > finderSupplier ) { 
		
		super( Type.LOGICAL_AND.finder() );
		
		this.finderSupplier = finderSupplier;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalAndOperations < UDM > newStore() {
		return new LogicalAndOperations< UDM >( this.finderSupplier );
	}

	@Override
	public LogicalAndOperations < UDM > toStore( SnPoint src, LogicalAndOperations < UDM > target ) {
		
		src.children.forEach( node -> {
			this.finderSupplier.newFinder( node, target.finderList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalAndOperations < UDM > src, SnPoint target ) {

		var array = src.finderList.toArray( new Finder[ src.finderList.size() ] );
//		 
		for( var finder : array ) {
			var node = finder.newTransformer().toNode( finder );
			target.children.add( node );  
		}
//		
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	
}
