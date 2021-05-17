package com.aladdindb.finder.logical;

import com.aladdindb.Type;
import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupport;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public class LogicalOrOperationsTransformer < UDM extends DataModel< UDM > > extends Transformer< LogicalOrOperations < UDM > > {

	
	private final  FinderSupport < UDM > finderSupport; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOrOperationsTransformer( FinderSupport < UDM > finderSupport ) { 
		
		super( Type.LOGICAL_OR.finder() );
		
		this.finderSupport = finderSupport;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalOrOperations < UDM > newModel() {
		return new LogicalOrOperations< UDM >( this.finderSupport );
	}

	@Override
	public LogicalOrOperations < UDM > toModel( SnPoint src, LogicalOrOperations < UDM > target ) {
		
		src.children.forEach( node -> {
			this.finderSupport.newFinder( node, target.finderList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalOrOperations < UDM > src, SnPoint target ) {

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
