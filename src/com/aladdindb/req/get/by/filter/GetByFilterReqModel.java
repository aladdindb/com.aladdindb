package com.aladdindb.req.get.by.filter;

import com.aladdindb.filter.Filter;
import com.aladdindb.req.ReqModel;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class GetByFilterReqModel <

	UDM 			extends DataModel	< UDM >,
	FILTER_MODEL 	extends Filter		< UDM, FILTER_MODEL>

> extends ReqModel < GetByFilterReqModel < UDM, FILTER_MODEL > > {

	public final Var < Integer > 		blockSize 	= new Var<>();
	public final Var < FILTER_MODEL> 	filter 		= new Var<>();
	
	public GetByFilterReqModel( String unitGroupID, int blockSize,  FILTER_MODEL filter) {
		super( unitGroupID );
		
		this.blockSize	.set( blockSize );
		this.filter		.set( filter	);
	}

	@Override
	public void fill( GetByFilterReqModel < UDM, FILTER_MODEL > model ) {
		super.fill( model );
		
		this.blockSize	.set( model.blockSize 	);
		this.filter		.set( model.filter 		);
	}

}
