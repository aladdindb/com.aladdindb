package com.aladdindb.sorter;

import com.aladdindb.finder.Type;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public class SorterListTransformer < UDM extends DataModel< UDM > > extends Transformer< SorterList < UDM > > {

	
	private final  SorterSupport< UDM > support; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public SorterListTransformer( SorterSupport < UDM > support ) { 
		
		super( Type.LIST.sorter() );
		
		this.support = support;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public SorterList < UDM > newModel() {
		return new SorterList< UDM >( this.support );
	}

	@Override
	public SorterList < UDM > toModel( SnPoint src, SorterList < UDM > target ) {
		
		src.children.forEach( node -> {
			this.support.newSorter( node, target.sorters :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( SorterList < UDM > src, SnPoint target ) {

		var sorterArray = src.sorters.toArray( new Sorter[ src.sorters.size() ] );
//		 
		for( var sorter : sorterArray ) {
			sorter.newTransformer().toNode( sorter, sorterNode -> target.children.add((SnPoint) sorterNode ) );
		}
//		
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	
}
