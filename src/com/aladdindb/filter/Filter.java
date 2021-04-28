package com.aladdindb.filter;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.units.models.Unit;

public interface Filter <

	UDM		extends DataModel 	< UDM 			>,
	MODEL 	extends Filter		< UDM, MODEL	>

> extends DataModel < MODEL > {
	
	public boolean prove( Unit< UDM > udm );
	
	public DataParser < MODEL > createParser();
	
}
