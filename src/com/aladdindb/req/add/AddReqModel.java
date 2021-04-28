package com.aladdindb.req.add;

import com.aladdindb.req.ReqModel;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class AddReqModel< UDM extends DataModel< UDM > > extends ReqModel< AddReqModel< UDM > > {

	
	public final Var< UDM >	unitData = new Var<>();

	public AddReqModel() {
		this( null, null );
	}
	
	public AddReqModel( String unitGroupID, UDM unitData ) {
		super( unitGroupID );
		this.unitData.set( unitData );
	}

	@Override
	public void fill( AddReqModel< UDM > model ) {
		this.unitData.set( model.unitData );
	}
	

}
