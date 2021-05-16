package com.aladdindb.finder;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.units.models.Unit;

public interface Finder <

	UDM				extends DataModel 	< UDM 				>,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL	>

> extends DataModel < FINDER_MODEL > {
	
	public boolean prove( Unit< UDM > udm );
	
	public Transformer < FINDER_MODEL > newTransformer();
	
}
