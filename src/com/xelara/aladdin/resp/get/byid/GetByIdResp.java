package com.xelara.aladdin.resp.get.byid;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.core.util.Var;


public class GetByIdResp < UDM extends DataModel < UDM > > implements DataModel< GetByIdResp < UDM > > {

	
	public final Var < Unit< UDM > > unit = new Var<>();

	
	public GetByIdResp() {
	}
	
	public GetByIdResp( Unit<UDM> unit ) {
		this.unit.set( unit );
	}
	
	@Override
	public void fill( GetByIdResp< UDM > model ) {
		this.unit.set( model.unit );
	}

}
