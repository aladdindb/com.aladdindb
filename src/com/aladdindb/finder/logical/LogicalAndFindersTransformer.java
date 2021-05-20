package com.aladdindb.finder.logical;

import com.aladdindb.Support;
import com.aladdindb.Type;
import com.aladdindb.finder.Finder;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public class LogicalAndFindersTransformer < UDM extends DataModel< UDM > > extends Transformer< LogicalAndFinders < UDM > > {

	
	private final  Support< UDM > support; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalAndFindersTransformer( Support< UDM > support ) { 
		
		super( Type.LOGICAL_AND.finder() );
		
		this.support = support;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalAndFinders < UDM > newModel() {
		return new LogicalAndFinders< UDM >( this.support );
	}

	@Override
	public LogicalAndFinders < UDM > toModel( SnPoint src, LogicalAndFinders < UDM > target ) {
		
		src.children.forEach( node -> {
			this.support.newFinder( node, target.finderList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalAndFinders < UDM > src, SnPoint target ) {

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