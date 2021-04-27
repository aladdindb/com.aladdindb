package com.xelara.aladdin.core.filter;

import com.xelara.aladdin.core.filter.logical.LogicalOperationsAndParser;
import com.xelara.aladdin.core.filter.logical.LogicalOperationsOrParser;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;


public abstract class FilterFactoryDefault < UDM extends DataModel< UDM > > implements FilterFactory < UDM > {

	 
	@Override
	public DataParser < ? extends Filter< UDM, ?> > createFilterParser( String filter ) {
		
		return 	filter.equals( FilterEnum.FILTER_LOGICAL_AND	.cmd() ) ? new LogicalOperationsAndParser	< UDM >( this ) : 
				filter.equals( FilterEnum.FILTER_LOGICAL_OR		.cmd() ) ? new LogicalOperationsOrParser	< UDM >( this ) : null;
	}	
	
	
}
