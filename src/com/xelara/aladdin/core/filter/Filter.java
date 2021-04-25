package com.xelara.aladdin.core.filter;

import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;

public interface Filter <

	UDM		extends DataModel 	< UDM 	>,
	MODEL 	extends Filter		< UDM, MODEL	>

> extends DataModel< MODEL > {
	
	public boolean prove( UDM udm );
	
	public DataParser < MODEL > createParser();
	
}
