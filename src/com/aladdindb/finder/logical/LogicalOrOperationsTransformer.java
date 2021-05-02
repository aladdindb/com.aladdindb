package com.aladdindb.finder.logical;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinnderType;
import com.aladdindb.finder.FinderSupplier;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;


public class LogicalOrOperationsTransformer < UDM extends DataModel< UDM > > extends DataTransformer< LogicalOrOperations < UDM > > {

	
	private final  FinderSupplier < UDM > factory; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOrOperationsTransformer( FinderSupplier < UDM > factory ) { 
		
		super( FinnderType.LOGICAL_OR.tagName() );
		
		this.factory = factory;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalOrOperations < UDM > newModel() {
		return new LogicalOrOperations< UDM >( this.factory );
	}

	@Override
	public LogicalOrOperations < UDM > toModel( SnPoint src, LogicalOrOperations < UDM > target ) {
		
		src.children.forEach( node -> {
			this.factory.createFinder( node, target.filterList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalOrOperations < UDM > src, SnPoint target ) {

		var array = src.filterList.toArray( new Finder[ src.filterList.size() ] );
//		 
		for( var filter : array ) {
			var node = filter.createTransformer().toNode( filter );
			target.children.add( node );  
		}
//		
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	
}
