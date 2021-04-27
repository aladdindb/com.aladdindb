package com.xelara.aladdin.core.filter;

import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;

public interface Filter <

	UDM		extends DataModel 	< UDM 			>,
	MODEL 	extends Filter		< UDM, MODEL	>

> extends DataModel < MODEL > {
	
	public boolean prove( Unit< UDM > udm );
	
	public DataParser < MODEL > createParser();
	
}
