package com.aladdindb.resp.get.by.id;

import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


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
