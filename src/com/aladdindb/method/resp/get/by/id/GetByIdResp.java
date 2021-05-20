package com.aladdindb.method.resp.get.by.id;

import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


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
