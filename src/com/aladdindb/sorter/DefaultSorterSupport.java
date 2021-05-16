package com.aladdindb.sorter;

import com.aladdindb.finder.Type;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;


public abstract class DefaultSorterSupport < UDM extends DataModel< UDM > > implements SorterSupport < UDM > {

	 
	@Override
	public Transformer < ? extends Sorter< UDM, ?> > newTransformer( String sorterType ) {
		
		return 	sorterType.equals( Type.LIST.sorter() ) ? new SorterListTransformer < UDM >( this ) : null; 
	}	
	
	
}
