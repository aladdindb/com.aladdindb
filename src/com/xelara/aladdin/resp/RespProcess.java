package com.xelara.aladdin.resp;

import com.xelara.aladdin.Genie;
import com.xelara.structure.DataModel;


public abstract class RespProcess < UDM extends DataModel < UDM > > implements Runnable { 

	
	public final Genie	< UDM > genie;

	
	public RespProcess( Genie < UDM > genie ) { 
		this.genie = genie;
	}
	
}
