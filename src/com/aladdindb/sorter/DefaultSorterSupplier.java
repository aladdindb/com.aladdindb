package com.aladdindb.sorter;

import com.aladdindb.finder.Type;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;


public abstract class DefaultSorterSupplier < UDM extends DataModel< UDM > > implements SorterSupplier < UDM > {

	 
	@Override
	public DataTransformer < ? extends Sorter< UDM, ?> > createFinderTransformer( String sorterName ) {
		
		return 	sorterName.equals( Type.LIST	.finder() ) ? new SorterListTransformer < UDM >( this ) : null; 
	}	
	
	
}
