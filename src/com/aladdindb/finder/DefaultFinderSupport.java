package com.aladdindb.finder;

import com.aladdindb.Type;
import com.aladdindb.finder.logical.LogicalAndOperationsTransformer;
import com.aladdindb.finder.logical.LogicalOrOperationsTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;


public abstract class DefaultFinderSupport < UDM extends DataModel< UDM > > implements FinderSupport < UDM > {

	 
	@Override
	public Transformer < ? extends Finder< UDM, ?> > newTransformer( String finderType ) {
		
		return 	finderType.equals( Type.LOGICAL_AND	.finder() ) ? new LogicalAndOperationsTransformer	< UDM >( this ) : 
				finderType.equals( Type.LOGICAL_OR	.finder() ) ? new LogicalOrOperationsTransformer	< UDM >( this ) : null;
	}	
	
	
}
