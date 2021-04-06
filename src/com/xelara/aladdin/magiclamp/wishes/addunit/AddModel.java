package com.xelara.aladdin.magiclamp.wishes.addunit;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.util.Var;

public class AddModel < UGM extends DataModel < UGM > > implements DataModel< AddModel < UGM> > {

	public final Var<String> 		userID 		= new Var<>();
	public final Var<String> 		unitGroupID = new Var<>();
	
	public final Var < UGM > data 	= new Var< UGM >();
	
	public AddModel() {
	}
	
	public AddModel( String unitGroupID, String userID, UGM cmdData ) {
		this.unitGroupID.set(unitGroupID);
		this.userID.set(userID);
		this.data.set(cmdData);
	}
	
	@Override
	public void fill( AddModel model ) {
		this.userID			.fill( model.userID );
		this.unitGroupID	.fill( model.unitGroupID );
		this.data		.fill( model.data );
	}

}
