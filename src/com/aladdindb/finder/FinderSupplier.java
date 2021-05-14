package com.aladdindb.finder;

import java.util.function.Consumer;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;


public interface FinderSupplier < UDM extends DataModel< UDM > > {
	
	

	public DataTransformer< ? extends Finder< UDM, ?> > newTransformer( String finder );
	
	
	default Finder< UDM, ? extends DataModel < ? > > newFinder( SnPoint finderNode ) {
		 
		var finderTransformer = this.newTransformer( finderNode.key.get() );
		   
		return finderTransformer != null ? (Finder< UDM, ? extends DataModel < ? > >) finderTransformer.toModel( finderNode ) : null;
		  
	}
	
	default void newFinder( SnPoint node, Consumer < Finder < UDM, ? extends DataModel<?> > > finderConsumer ) {
		var rv = newFinder( node );
		if( rv != null ) finderConsumer.accept( rv );
	}
	
	
	
}
