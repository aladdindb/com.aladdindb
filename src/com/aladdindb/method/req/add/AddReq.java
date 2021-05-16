package com.aladdindb.method.req.add;

import com.aladdindb.method.req.Req;
import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;


public class AddReq< UDM extends Store< UDM > > extends Req< AddReq< UDM > > {

	
	public final Var< UDM >	unitData = new Var<>();

	public AddReq() {
		this( null, null );
	}
	
	public AddReq( String unitGroupID, UDM unitData ) {
		super( unitGroupID );
		this.unitData.set( unitData );
	}

	@Override
	public void fill( AddReq< UDM > model ) {
		this.unitData.set( model.unitData );
	}
	

}
