package com.xelara.aladdin.resp.remove;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.core.util.Var;


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
