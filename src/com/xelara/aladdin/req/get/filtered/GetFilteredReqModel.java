package com.xelara.aladdin.req.get.filtered;

import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.req.ReqModel;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;


public class GetFilteredReqModel <

	UDM 			extends DataModel	< UDM >,
	FILTER_MODEL 	extends Filter		< UDM, FILTER_MODEL>

> extends ReqModel < GetFilteredReqModel < UDM, FILTER_MODEL > > {

	public final Var < Integer > 		blockSize 	= new Var<>();
	public final Var < FILTER_MODEL> 	filter 		= new Var<>();
	
	public GetFilteredReqModel( String unitGroupID, int blockSize,  FILTER_MODEL filter) {
		super( unitGroupID );
		
		this.blockSize	.set( blockSize );
		this.filter		.set( filter	);
	}

	@Override
	public void fill( GetFilteredReqModel < UDM, FILTER_MODEL > model ) {
		super.fill( model );
		
		this.blockSize	.set( model.blockSize 	);
		this.filter		.set( model.filter 		);
	}

}
