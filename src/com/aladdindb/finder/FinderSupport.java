package com.aladdindb.finder;

import java.util.function.Consumer;

import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public interface FinderSupport < UDM extends Store< UDM > > {
	
	

	public Transformer< ? extends Finder< UDM, ?> > newTransformer( String finder );
	
	
	default Finder< UDM, ? extends Store < ? > > newFinder( SnPoint finderNode ) {
		 
		var finderTransformer = this.newTransformer( finderNode.key.get() );
		   
		return finderTransformer != null ? (Finder< UDM, ? extends Store < ? > >) finderTransformer.toStore( finderNode ) : null;
		  
	}
	
	default void newFinder( SnPoint node, Consumer < Finder < UDM, ? extends Store<?> > > finderConsumer ) {
		var rv = newFinder( node );
		if( rv != null ) finderConsumer.accept( rv );
	}
	
	
	
}
