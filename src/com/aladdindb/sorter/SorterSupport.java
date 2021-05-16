package com.aladdindb.sorter;

import java.util.function.Consumer;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;


public interface SorterSupport < UDM extends DataModel< UDM > > {
	
	

	public Transformer< ? extends Sorter< UDM, ? > > newTransformer( String finder );
	
	
	default Sorter< UDM, ? extends DataModel < ? > > newSorter( SnPoint sorterNode ) {
		
		var transformer = this.newTransformer( sorterNode.key.get() );
		    
		return transformer != null ? ( Sorter< UDM, ? extends DataModel < ? > >) transformer.toModel( sorterNode ) : null;
		  
	}
	
	default void newSorter( SnPoint node, Consumer < Sorter < UDM, ? extends DataModel < ? > > > sorterConsumer ) {
		var rv = newSorter( node );
		if( rv != null ) sorterConsumer.accept( rv );
	}
	
	
	
}
