package com.aladdindb.sorter;

import java.util.List;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.units.Units;

public interface Sorter <

	UDM 	extends DataModel 	< UDM >, 
	MODEL 	extends Sorter		< UDM, MODEL	>

> extends DataModel < MODEL > {
	
	public List	< String > 				sort				( List<String> unitIdList );
	public List	< List < String > > 	sortBlockWise		( List<String> unitIdList );
	
	public void 						setUnits			( Units< UDM > units );
	
	public DataTransformer < MODEL > 	newTransformer	(); 
	
	
}
