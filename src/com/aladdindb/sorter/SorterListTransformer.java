package com.aladdindb.sorter;

import com.aladdindb.finder.Type;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;


public class SorterListTransformer < UDM extends DataModel< UDM > > extends DataTransformer< SorterList < UDM > > {

	
	private final  SorterSupplier< UDM > finderSupplier; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public SorterListTransformer( SorterSupplier < UDM > finderSupplier ) { 
		
		super( Type.LOGICAL_AND.finder() );
		
		this.finderSupplier = finderSupplier;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public SorterList < UDM > newModel() {
		return new SorterList< UDM >( this.finderSupplier );
	}

	@Override
	public SorterList < UDM > toModel( SnPoint src, SorterList < UDM > target ) {
		
		src.children.forEach( node -> {
			this.finderSupplier.createFinder( node, target.sorters :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( SorterList < UDM > src, SnPoint target ) {

		var array = src.sorters.toArray( new Sorter[ src.sorters.size() ] );
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
