package com.aladdindb.method.req.add;

import com.aladdindb.method.req.Req;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class AddReq< UDM extends DataModel< UDM > > extends Req< AddReq< UDM > > {

	
	public final Var< UDM >	unitData = new Var<>();

	public AddReq() {
		this( null, null );
	}
	
	public AddReq( String storeId, UDM unitData ) {
		super( storeId );
		this.unitData.set( unitData );
	}

	@Override
	public void fill( AddReq< UDM > model ) {
		this.unitData.set( model.unitData );
	}
	

}
