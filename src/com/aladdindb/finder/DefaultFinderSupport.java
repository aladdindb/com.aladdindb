package com.aladdindb.finder;

import com.aladdindb.finder.logical.LogicalAndOperationsTransformer;
import com.aladdindb.finder.logical.LogicalOrOperationsTransformer;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;


public abstract class DefaultFinderSupport < UDM extends Store< UDM > > implements FinderSupport < UDM > {

	 
	@Override
	public Transformer < ? extends Finder< UDM, ?> > newTransformer( String finderType ) {
		
		return 	finderType.equals( Type.LOGICAL_AND	.finder() ) ? new LogicalAndOperationsTransformer	< UDM >( this ) : 
				finderType.equals( Type.LOGICAL_OR	.finder() ) ? new LogicalOrOperationsTransformer	< UDM >( this ) : null;
	}	
	
	
}
