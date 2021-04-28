package com.aladdindb.req.update;

import com.aladdindb.req.ReqModel;
import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


public class UpdateReqModel< UDM extends DataModel< UDM > > extends ReqModel< UpdateReqModel< UDM > > {

	
	public final Var< Unit < UDM > >unitData = new Var<>();

	public UpdateReqModel() {
		this( null, null );
	}
	
	public UpdateReqModel( String unitGroupID, Unit < UDM > unit ) {
		super( unitGroupID );
		this.unitData.set( unit );
	}

	@Override
	public void fill( UpdateReqModel< UDM > model ) {
		this.unitData.set( model.unitData );
	}
	

}
