package com.aladdindb.method.resp;

import com.aladdindb.Genie;
import com.aladdindb.structure.Store;


public abstract class RespProcess < UDM extends Store < UDM > > implements Runnable { 

	
	public final Genie	< UDM > genie;

	
	public RespProcess( Genie < UDM > genie ) { 
		this.genie = genie;
	}
	
}
