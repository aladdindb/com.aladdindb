package com.xelara.aladdin;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;

import com.xelara.aladdin.core.UnitIdBlockNavi;
import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.core.filter.FilterFactory;
import com.xelara.aladdin.core.units.Units;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.aladdin.resp.add.AddRespProcess;
import com.xelara.aladdin.resp.get.GetAllRespProcess;
import com.xelara.aladdin.resp.get.block.GetBlockRespProcess;
import com.xelara.aladdin.resp.get.by.filter.GetByFilterRespProcess;
import com.xelara.aladdin.resp.get.by.id.GetByIdRespProcess;
import com.xelara.aladdin.resp.remove.RemoveRespProcess;
import com.xelara.aladdin.resp.update.UpdateRespProcess;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 
	

	public final HashMap< String, UnitIdBlockNavi > unitIdBlockNav = new HashMap<>();
	
	public final	Units		< UDM > units;
	public final	DataParser 	< UDM > dataParser;

	public final 	FilterFactory< UDM > filterFac;
	
	public final Var < SnPoint > 				reqNode 		= new Var<>();
	public final Var < Consumer < String > > 	respConsumer 	= new Var<>();
	
	
	public Genie( Path dbPath, DataParser < UDM > dataParser, FilterFactory< UDM > filterFac ) {
		this.dataParser		= dataParser;
		this.units			= new Units	< UDM > ( dbPath, dataParser );
		
		this.filterFac 		= filterFac;
		
	}
    
	@Override
	public void run() {
		this.reqNode.get( reqNode -> {
			RespProcess< UDM > process = switch( reqNode.key.get() ) {
			
				case Req.ADD 			-> new AddRespProcess			< UDM > ( this );
				case Req.GET_BY_ID 		-> new GetByIdRespProcess		< UDM > ( this );
				case Req.GET_BY_FILTER	-> new GetByFilterRespProcess	<> 		( this);
				case Req.GET_ALL 		-> new GetAllRespProcess		< UDM > ( this );
				case Req.GET_ALL_BLOCK	-> new GetBlockRespProcess	< UDM > ( this );
				case Req.UPDATE			-> new UpdateRespProcess		< UDM > ( this );
				case Req.REMOVE			-> new RemoveRespProcess		< UDM > ( this );
				
				default -> throw new IllegalArgumentException("Unexpected value: " + reqNode.key.get() ); 
			};
			
			process.run();
		});
	}
    
}
