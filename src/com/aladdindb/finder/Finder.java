package com.aladdindb.finder;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.units.models.Unit;

public interface Finder <

	UDM		extends DataModel 	< UDM 			>,
	MODEL 	extends Finder		< UDM, MODEL	>

> extends DataModel < MODEL > {
	
	public boolean prove( Unit< UDM > udm );
	
	public DataTransformer < MODEL > createTransformer();
	
}
