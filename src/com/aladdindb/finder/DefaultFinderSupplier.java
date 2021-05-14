package com.aladdindb.finder;

import com.aladdindb.finder.logical.LogicalAndOperationsTransformer;
import com.aladdindb.finder.logical.LogicalOrOperationsTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;


public abstract class DefaultFinderSupplier < UDM extends DataModel< UDM > > implements FinderSupplier < UDM > {

	 
	@Override
	public DataTransformer < ? extends Finder< UDM, ?> > newTransformer( String finderName ) {
		
		return 	finderName.equals( Type.LOGICAL_AND	.finder() ) ? new LogicalAndOperationsTransformer	< UDM >( this ) : 
				finderName.equals( Type.LOGICAL_OR	.finder() ) ? new LogicalOrOperationsTransformer	< UDM >( this ) : null;
	}	
	
	
}
