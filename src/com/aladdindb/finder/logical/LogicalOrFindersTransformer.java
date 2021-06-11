package com.aladdindb.finder.logical;

import com.aladdindb.FinderSupport;
import com.aladdindb.finder.Finder;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public class LogicalOrFindersTransformer < UDM extends DataModel< UDM > > extends Transformer< LogicalOrFinders < UDM > > {

	
	private final  FinderSupport< UDM > support; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOrFindersTransformer( FinderSupport< UDM > support ) { 
		
		super( Finder.LOCGICAL_OR );
		
		this.support = support;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalOrFinders < UDM > newModel() {
		return new LogicalOrFinders< UDM >( this.support );
	}

	@Override
	public LogicalOrFinders < UDM > toModel( SnPoint src, LogicalOrFinders < UDM > target ) {
		
		src.children.forEach( node -> {
			this.support.newFinder( node, target.finderList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalOrFinders < UDM > src, SnPoint target ) {

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
