package com.aladdindb.filter;

import com.aladdindb.filter.logical.LogicalOperationsAndParser;
import com.aladdindb.filter.logical.LogicalOperationsOrParser;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;


public abstract class FilterFactoryDefault < UDM extends DataModel< UDM > > implements FilterFactory < UDM > {

	 
	@Override
	public DataParser < ? extends Filter< UDM, ?> > createFilterParser( String filter ) {
		
		return 	filter.equals( FilterEnum.FILTER_LOGICAL_AND	.cmd() ) ? new LogicalOperationsAndParser	< UDM >( this ) : 
				filter.equals( FilterEnum.FILTER_LOGICAL_OR		.cmd() ) ? new LogicalOperationsOrParser	< UDM >( this ) : null;
	}	
	
	
}
