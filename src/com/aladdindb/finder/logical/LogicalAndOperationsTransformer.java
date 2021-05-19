package com.aladdindb.finder.logical;

import com.aladdindb.Support;
import com.aladdindb.Type;
import com.aladdindb.finder.Finder;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public class LogicalAndOperationsTransformer < UDM extends DataModel< UDM > > extends Transformer< LogicalAndOperations < UDM > > {

	
	private final  Support< UDM > support; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalAndOperationsTransformer( Support< UDM > support ) { 
		
		super( Type.LOGICAL_AND.finder() );
		
		this.support = support;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalAndOperations < UDM > newModel() {
		return new LogicalAndOperations< UDM >( this.support );
	}

	@Override
	public LogicalAndOperations < UDM > toModel( SnPoint src, LogicalAndOperations < UDM > target ) {
		
		src.children.forEach( node -> {
			this.support.newFinder( node, target.finderList :: add );
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
