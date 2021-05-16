package com.aladdindb.method.req.update;

import com.aladdindb.method.req.Req;
import com.aladdindb.structure.Store;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


public class UpdateReq< UDM extends Store< UDM > > extends Req< UpdateReq< UDM > > {

	
	public final Var< Unit < UDM > >unitData = new Var<>();

	public UpdateReq() {
		this( null, null );
	}
	
	public UpdateReq( String unitGroupID, Unit < UDM > unit ) {
		super( unitGroupID );
		this.unitData.set( unit );
	}

	@Override
	public void fill( UpdateReq< UDM > model ) {
		this.unitData.set( model.unitData );
	}
	

}
