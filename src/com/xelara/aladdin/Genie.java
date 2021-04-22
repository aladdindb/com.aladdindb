package com.xelara.aladdin;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.core.UnitIdBlockNavi;
import com.xelara.aladdin.core.units.Units;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.aladdin.resp.add.AddRespProcess;
import com.xelara.aladdin.resp.get.all.GetAllRespProcess;
import com.xelara.aladdin.resp.get.all.block.GetAllBlockRespProcess;
import com.xelara.aladdin.resp.get.byid.GetByIdRespProcess;
import com.xelara.aladdin.resp.remove.RemoveRespProcess;
import com.xelara.aladdin.resp.update.UpdateRespProcess;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 
	

	public final HashMap< String, UnitIdBlockNavi > unitIdBlockNav = new HashMap<>();
	
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
			
				case Req.ADD 			-> new AddRespProcess			< UDM > ( this );
				case Req.GET_BY_ID 		-> new GetByIdRespProcess		< UDM > ( this );
				case Req.GET_ALL 		-> new GetAllRespProcess		< UDM > ( this );
				case Req.GET_ALL_BLOCK	-> new GetAllBlockRespProcess	< UDM > ( this );
				case Req.UPDATE			-> new UpdateRespProcess		< UDM > ( this );
				case Req.REMOVE			-> new RemoveRespProcess		< UDM > ( this );
				
				default -> throw new IllegalArgumentException("Unexpected value: " + reqNode.key.get() ); 
			};
			
			process.run();
		});
	}
    
}
