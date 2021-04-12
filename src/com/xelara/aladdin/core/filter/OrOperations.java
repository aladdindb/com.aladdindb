package com.xelara.aladdin.core.filter;

import com.xelara.aladdin.core.DataModel;

public class OrOperations < DATA_MODEL extends DataModel< DATA_MODEL > > implements Filter< DATA_MODEL >  {
	
	private final Filter< DATA_MODEL >[] verifierers;
	
	public OrOperations( Filter< DATA_MODEL >...verifierers ) {
		this.verifierers = verifierers;
	}
	
	public boolean prove( DATA_MODEL model ) {
		boolean rv = false;
		int i = 0; 
		do {
			rv = this.verifierers[i++].prove( model );
		} while( i < this.verifierers.length && !rv );
		return rv;
	}
	
}
