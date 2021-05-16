package com.aladdindb.sorter;

import java.util.List;

import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.units.Units;

public interface Sorter <

	UDM 	extends Store 	< UDM >, 
	MODEL 	extends Sorter		< UDM, MODEL	>

> extends Store < MODEL > {
	
	public List	< String > 				sort				( List<String> unitIdList );
	public List	< List < String > > 	sortBlockWise		( List<String> unitIdList );
	
	public void 						setUnits			( Units< UDM > units );
	
	public Transformer < MODEL > 		newTransformer		(); 
	
	
}
