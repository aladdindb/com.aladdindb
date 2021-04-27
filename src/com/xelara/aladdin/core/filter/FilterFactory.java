package com.xelara.aladdin.core.filter;

import java.util.function.Consumer;

import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;


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
