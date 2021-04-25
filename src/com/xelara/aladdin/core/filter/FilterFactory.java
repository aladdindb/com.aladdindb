package com.xelara.aladdin.core.filter;

import java.util.function.Consumer;

import com.xelara.structure.DataModel;
import com.xelara.structure.sn.SnPoint;


public interface FilterFactory < UDM extends DataModel< UDM > > {
	
	public Filter < UDM, ? extends DataModel<?> > createFilter( SnPoint node );

	default void createFilter( SnPoint node, Consumer < Filter < UDM, ? extends DataModel<?> > > consumer ) {
		var rv = createFilter( node );
		if( rv != null ) consumer.accept( rv );
	}
	
	
}
