package com.aladdindb.sorter;

import java.util.List;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.units.Units;

public interface Sorter <

	UDM 			extends DataModel 	< UDM >, 
	SORTER_MODEL 	extends Sorter		< UDM, SORTER_MODEL	>

> extends DataModel < SORTER_MODEL > {
	
	public List	< String > 				sort				( List<String> unitIdList );
	public List	< List < String > > 	sortBlockWise		( List<String> unitIdList );
	
	public void 						setUnits			( Units< UDM > units );
	
	public Transformer < SORTER_MODEL > newTransformer		(); 
	
	
}
