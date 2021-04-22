package com.xelara.aladdin.core.filter;

import com.xelara.structure.DataModel;

public class LogicalAndOperations < DATA_MODEL extends DataModel< DATA_MODEL > > implements Filter< DATA_MODEL >  {
	
	
	private final Filter< DATA_MODEL >[] filters;
	
	
	public LogicalAndOperations( Filter< DATA_MODEL >...filters ) {
		this.filters = filters;
	}
	
	public boolean prove( DATA_MODEL model ) {
		boolean rv = true;
		int i = 0; 
		do {
			rv = this.filters[i++].prove( model );
		} while( i < this.filters.length && rv );
		
		return rv;
	}
	
}
