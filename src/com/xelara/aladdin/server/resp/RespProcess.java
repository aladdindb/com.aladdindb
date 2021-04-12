package com.xelara.aladdin.server.resp;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.server.Genie;


public abstract class RespProcess < UDM extends DataModel < UDM > > implements Runnable { 

	
	public final Genie	< UDM > genie;

	
	public RespProcess( Genie < UDM > genie ) { 
		this.genie = genie;
	}
	
}
