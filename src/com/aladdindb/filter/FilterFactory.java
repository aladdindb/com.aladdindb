package com.aladdindb.filter;

import java.util.function.Consumer;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;


public interface FilterFactory < UDM extends DataModel< UDM > > {
	

	public DataParser< ? extends Filter< UDM, ?> > createFilterParser( String filter );
	
	
	default Filter< UDM, ? extends DataModel < ? > > createFilter( SnPoint filterNode ) {
		
		var filterParser = this.createFilterParser( filterNode.key.get() );
		
		return filterParser != null ? (Filter< UDM, ? extends DataModel < ? > >) filterParser.toModel( filterNode ) : null;
		
	}
	
	default void createFilter( SnPoint node, Consumer < Filter < UDM, ? extends DataModel<?> > > consumer ) {
		var rv = createFilter( node );
		if( rv != null ) consumer.accept( rv );
	}
	
	
	
}
