package com.aladdindb.method.req.update;

import com.aladdindb.method.req.Req;
import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


public class UpdateReq< UDM extends DataModel< UDM > > extends Req< UpdateReq< UDM > > {

	
	public final Var< Unit < UDM > >unitData = new Var<>();

	public UpdateReq() {
		this( null, null );
	}
	
	public UpdateReq( String storeId, Unit < UDM > unit ) {
		super( storeId );
		this.unitData.set( unit );
	}

	@Override
	public void fill( UpdateReq< UDM > model ) {
		this.unitData.set( model.unitData );
	}
	

}
