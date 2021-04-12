package com.xelara.aladdin.client.req.update;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.core.util.Var;

public class UpdateReq < UDM extends DataModel < UDM > > implements DataModel< UpdateReq < UDM > > {

	public final Var< String > 			unitGroupID = new Var<>();
	public final Var < Unit < UDM > >	unitData 	= new Var<>();

	public UpdateReq() {
	}
	
	public UpdateReq( String unitGroupID, Unit<UDM> unit ) {
		this.unitGroupID	.set( unitGroupID );
		this.unitData		.set( unit );
	}
	
	@Override
	public void fill( UpdateReq< UDM > model ) {
		this.unitGroupID	.set( model.unitGroupID );
		this.unitData		.set( model.unitData );
	}

}
