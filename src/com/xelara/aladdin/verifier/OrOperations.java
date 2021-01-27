package com.xelara.aladdin.verifier;

import com.xelara.aladdin.unit.model.DataModel;

public class OrOperations < DATA_MODEL extends DataModel< DATA_MODEL > > implements Verifier< DATA_MODEL >  {
	
	private final Verifier< DATA_MODEL >[] verifierers;
	
	public OrOperations( Verifier< DATA_MODEL >...verifierers ) {
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
