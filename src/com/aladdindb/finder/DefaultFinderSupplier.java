package com.aladdindb.finder;

import com.aladdindb.finder.logical.LogicalAndOperationsTransformer;
import com.aladdindb.finder.logical.LogicalOrOperationsTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;


public abstract class DefaultFinderSupplier < UDM extends DataModel< UDM > > implements FinderSupplier < UDM > {

	 
	@Override
	public DataTransformer < ? extends Finder< UDM, ?> > createFinderTransformer( String finderName ) {
		
		return 	finderName.equals( FinnderType.LOGICAL_AND	.tagName() ) ? new LogicalAndOperationsTransformer	< UDM >( this ) : 
				finderName.equals( FinnderType.LOGICAL_OR	.tagName() ) ? new LogicalOrOperationsTransformer	< UDM >( this ) : null;
	}	
	
	
}
