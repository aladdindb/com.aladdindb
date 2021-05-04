package com.aladdindb.method.req.search;

import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.Req;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class SearchReq <

	UDM 			extends DataModel	< UDM >,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL>

> extends Req < SearchReq < UDM, FINDER_MODEL > > {

	public final Var < Integer > 		blockSize 	= new Var<>();
	public final Var < FINDER_MODEL> 	finder 		= new Var<>();
	
	public SearchReq( String unitGroupID, int blockSize,  FINDER_MODEL finder) {
		super( unitGroupID );
		
		this.blockSize	.set( blockSize );
		this.finder		.set( finder	);
	}

	@Override
	public void fill( SearchReq < UDM, FINDER_MODEL > model ) {
		super.fill( model );
		
		this.blockSize	.set( model.blockSize 	);
		this.finder		.set( model.finder 		);
	}

}
