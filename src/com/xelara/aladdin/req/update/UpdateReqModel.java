package com.xelara.aladdin.req.update;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.req.ReqModel;
import com.xelara.core.util.Var;


public class UpdateReqModel< UDM extends DataModel< UDM > > extends ReqModel< UpdateReqModel< UDM > > {

	
	public final Var< Unit < UDM > >unitData = new Var<>();

	public UpdateReqModel() {
		this( null, null );
	}
	
	public UpdateReqModel( String unitGroupID, Unit < UDM > unit ) {
		super( unitGroupID );
		this.unitData.set( unit );
	}

	@Override
	public void fill( UpdateReqModel< UDM > model ) {
		this.unitData.set( model.unitData );
	}
	

}
