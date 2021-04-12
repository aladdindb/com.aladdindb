package com.xelara.aladdin.client.req.add;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

public class AddReq < UDM extends DataModel < UDM > > implements DataModel< AddReq < UDM > > {

	public final Var< String > 		unitGroupID = new Var<>();
	public final Var < UDM > 		unitData 	= new Var< UDM >();

	public AddReq() {
	}
	
	public AddReq( String unitGroupID, UDM unitData ) {
		this.unitGroupID	.set( unitGroupID );
		this.unitData		.set( unitData );
	}
	
	@Override
	public void fill( AddReq< UDM > model ) {
		this.unitGroupID	.set( model.unitGroupID );
		this.unitData		.set( model.unitData );
	}

}
