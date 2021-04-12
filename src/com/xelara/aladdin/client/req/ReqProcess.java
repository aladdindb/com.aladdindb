package com.xelara.aladdin.client.req;

import java.util.function.Consumer;

import com.xelara.aladdin.client.UnitsChannel;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.server.resp.add.AddRespParser;
import com.xelara.aladdin.server.resp.getbyid.GetByIdRespParser;
import com.xelara.core.util.Var;
import com.xelara.structure.xml.XML;

public abstract class ReqProcess < UDM extends DataModel < UDM >, RESP > implements Runnable { 

	public final Var < Consumer < RESP > > 	respConsumer = new Var<>();
	
	public final UnitsChannel< UDM > 		unitsChanel;
	
	
	public ReqProcess( UnitsChannel< UDM > unitsChanel ) {
		this.unitsChanel 	= unitsChanel;
	}
	
}
