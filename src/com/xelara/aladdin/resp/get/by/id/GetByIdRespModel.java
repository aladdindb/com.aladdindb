package com.xelara.aladdin.resp.get.by.id;

import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;


public class GetByIdRespModel < UDM extends DataModel < UDM > > implements DataModel< GetByIdRespModel < UDM > > {

	
	public final Var < Unit< UDM > > unit = new Var<>();

	
	public GetByIdRespModel() {
	}
	
	public GetByIdRespModel( Unit<UDM> unit ) {
		this.unit.set( unit );
	}
	
	@Override
	public void fill( GetByIdRespModel< UDM > model ) {
		this.unit.set( model.unit );
	}

}
