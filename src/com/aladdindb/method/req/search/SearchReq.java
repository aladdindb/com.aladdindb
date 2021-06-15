package com.aladdindb.method.req.search;

import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.Req;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class SearchReq <

	UDM 			extends DataModel	< UDM >,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL >,
	SORTER_MODEL 	extends Sorter		< UDM, SORTER_MODEL >

> extends Req < SearchReq < UDM, FINDER_MODEL, SORTER_MODEL > > {

	public final Var < Integer > 		blockSize 	= new Var<>();
	public final Var < FINDER_MODEL> 	finder 		= new Var<>();
	public final Var < SORTER_MODEL> 	sorter 		= new Var<>();

	public SearchReq() {
		super( null );
	}
	
	public SearchReq( String storeId, int blockSize,  FINDER_MODEL finder, SORTER_MODEL sorter ) {
		super( storeId );
		
		this.blockSize	.set( blockSize );
		this.finder		.set( finder	);
		this.sorter		.set( sorter	);
	}

	@Override
	public void fill( SearchReq < UDM, FINDER_MODEL, SORTER_MODEL > model ) {
		super.fill( model );
		
		this.blockSize	.set( model.blockSize 	);
		this.finder		.set( model.finder 		);
		this.sorter		.set( model.sorter 		);
	}

}
