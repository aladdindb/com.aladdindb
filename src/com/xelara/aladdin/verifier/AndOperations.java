package com.xelara.aladdin.verifier;

import com.xelara.aladdin.unit.model.DataModel;

public class AndOperations < DATA_MODEL extends DataModel< DATA_MODEL > > implements Verifier< DATA_MODEL >  {
	
	private final Verifier< DATA_MODEL >[] verifierers;
	
	public AndOperations( Verifier< DATA_MODEL >...verifierers ) {
		this.verifierers = verifierers;
	}
	
	public boolean prove( DATA_MODEL model ) {
		boolean rv = true;
		int i = 0; 
		do {
			rv = this.verifierers[i].prove( model );
			i++;
		} while( i < this.verifierers.length && rv );
		
		return rv;
	}
	
}
