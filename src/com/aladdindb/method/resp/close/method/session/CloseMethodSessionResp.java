package com.aladdindb.method.resp.close.method.session;

import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;


public class CloseMethodSessionResp  implements Store< CloseMethodSessionResp  > {

	
	public final Var<Boolean> successful = new Var<>();
	
	@Override
	public void fill( CloseMethodSessionResp model ) {
		
		this.successful.set( model.successful );
		
	}
	

}
