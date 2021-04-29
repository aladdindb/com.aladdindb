package com.aladdindb.method.resp.remove;

import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


public class RemoveResp < UDM extends DataModel < UDM > > implements DataModel< RemoveResp < UDM > > {

	
	public final Var < Unit< UDM > > unit = new Var<>();

	
	public RemoveResp() {
	}
	
	public RemoveResp( Unit<UDM> unit ) {
		this.unit.set( unit );
	}
	
	@Override
	public void fill( RemoveResp< UDM > model ) {
		this.unit.set( model.unit );
	}

}
