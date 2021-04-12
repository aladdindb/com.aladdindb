package com.xelara.aladdin.server;

import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.client.req.Req;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.core.units.Units;
import com.xelara.aladdin.server.resp.RespProcess;
import com.xelara.aladdin.server.resp.add.AddRespProcess;
import com.xelara.aladdin.server.resp.getbyid.GetByIdRespProcess;
import com.xelara.aladdin.server.resp.remove.RemoveResp;
import com.xelara.aladdin.server.resp.remove.RemoveRespProcess;
import com.xelara.aladdin.server.resp.update.UpdateRespProcess;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 
	
	
	public final	Units		< UDM > units;
	public final	DataParser 	< UDM > dataParser;

	
	public final Var < SnPoint > 				reqNode 		= new Var<>();
	public final Var < Consumer < String > > 	respConsumer 	= new Var<>();
	
	
	public Genie( Path dbPath, DataParser < UDM > dataParser ) {
		this.dataParser		= dataParser;
		this.units			= new Units	< UDM > ( dbPath, dataParser );
	}
    
	@Override
	public void run() {
		
		this.reqNode.get( reqNode -> {
			RespProcess< UDM > process = switch( reqNode.key.get() ) {
			
				case Req.ADD 			-> new AddRespProcess		< UDM > ( this );
				case Req.GET_BY_ID 		-> new GetByIdRespProcess	< UDM > ( this );
				case Req.UPDATE			-> new UpdateRespProcess	< UDM > ( this );
				case Req.REMOVE			-> new RemoveRespProcess	< UDM > ( this );
				
				default -> throw new IllegalArgumentException("Unexpected value: " + reqNode.key.get() ); 
			};
			process.run();
		});
	}
	

//	public void getAllUnits() {
//		units.forEach( unitListModel :: add );
//		parseUnitList();
//	}

    
}
