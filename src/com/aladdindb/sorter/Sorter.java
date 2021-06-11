package com.aladdindb.sorter;

import java.util.List;

import com.aladdindb.store.Store;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;

public interface Sorter <

	UDM 			extends DataModel 	< UDM >, 
	SORTER_MODEL 	extends Sorter		< UDM, SORTER_MODEL	>

> extends DataModel < SORTER_MODEL > {
	
	public static final String NAME 	= "Sorter";
	public static final String LIST 	= NAME+".list";
	
	public List	< String > 				sort				( List<String> unitIdList );
	public List	< List < String > > 	sortBlockWise		( List<String> unitIdList );
	
	public void 						setStore			( Store< UDM > store );
	
	public Transformer < SORTER_MODEL > newTransformer		(); 
	
	
}
