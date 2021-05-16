package com.aladdindb.method.resp.get.by.id;

import com.aladdindb.structure.Store;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


public class GetByIdResp < UDM extends Store < UDM > > implements Store< GetByIdResp < UDM > > {

	
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
