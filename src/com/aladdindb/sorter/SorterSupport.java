package com.aladdindb.sorter;

import java.util.function.Consumer;

import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public interface SorterSupport < UDM extends Store< UDM > > {
	
	

	public Transformer< ? extends Sorter< UDM, ? > > newTransformer( String finder );
	
	
	default Sorter< UDM, ? extends Store < ? > > newSorter( SnPoint sorterNode ) {
		
		var transformer = this.newTransformer( sorterNode.key.get() );
		    
		return transformer != null ? ( Sorter< UDM, ? extends Store < ? > >) transformer.toStore( sorterNode ) : null;
		  
	}
	
	default void newSorter( SnPoint node, Consumer < Sorter < UDM, ? extends Store < ? > > > sorterConsumer ) {
		var rv = newSorter( node );
		if( rv != null ) sorterConsumer.accept( rv );
	}
	
	
	
}
