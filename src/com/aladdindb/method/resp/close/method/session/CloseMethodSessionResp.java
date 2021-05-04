package com.aladdindb.method.resp.close.method.session;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


public class CloseMethodSessionResp  implements DataModel< CloseMethodSessionResp  > {

	
	public final Var<Boolean> successful = new Var<>();
	
	@Override
	public void fill( CloseMethodSessionResp model ) {
		
		this.successful.set( model.successful );
		
	}
	

}
