package com.aladdindb.sorter;

import java.util.function.Consumer;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;


public interface SorterSupplier < UDM extends DataModel< UDM > > {
	
	

	public DataTransformer< ? extends Sorter< UDM, ?> > createFinderTransformer( String finder );
	
	
	default Sorter< UDM, ? extends DataModel < ? > > createFinder( SnPoint finderNode ) {
		
		var finderTransformer = this.createFinderTransformer( finderNode.key.get() );
		   
		return finderTransformer != null ? (Sorter< UDM, ? extends DataModel < ? > >) finderTransformer.toModel( finderNode ) : null;
		  
	}
	
	default void createFinder( SnPoint node, Consumer < Sorter < UDM, ? extends DataModel<?> > > finderConsumer ) {
		var rv = createFinder( node );
		if( rv != null ) finderConsumer.accept( rv );
	}
	
	
	
}
