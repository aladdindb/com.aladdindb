package com.aladdindb.finder;

import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.units.models.Unit;

public interface Finder <

	UDM				extends Store 	< UDM 				>,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL	>

> extends Store < FINDER_MODEL > {
	
	public boolean prove( Unit< UDM > udm );
	
	public Transformer < FINDER_MODEL > newTransformer();
	
}
