package com.xelara.aladdin;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;

import com.xelara.aladdin.core.UnitIdBlockNavi;
import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.core.filter.FilterFactory;
import com.xelara.aladdin.core.units.Units;
import com.xelara.aladdin.req.Cmd;
import com.xelara.aladdin.resp.RespProcess;
import com.xelara.aladdin.resp.add.AddRespProcess;
import com.xelara.aladdin.resp.get.GetAllRespProcess;
import com.xelara.aladdin.resp.get.GetByFilterRespProcess;
import com.xelara.aladdin.resp.get.block.GetBlockRespProcess;
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
			
			RespProcess< UDM > process = this.createRespProcess( reqNode.key.get() );
			
			if( process != null ) process.run();
		});
	}
	
	private RespProcess< UDM > createRespProcess( String reqCmd ) { 
		
		return 	reqCmd.equals( Cmd.ADD				.req() ) ? new AddRespProcess 			< UDM > ( this ) :
				reqCmd.equals( Cmd.GET_BY_ID		.req() ) ? new GetByIdRespProcess 		< UDM > ( this ) :
				reqCmd.equals( Cmd.GET_BY_FILTER	.req() ) ? new GetByFilterRespProcess 	<     > ( this ) :
				reqCmd.equals( Cmd.GET_ALL			.req() ) ? new GetAllRespProcess 		< UDM > ( this ) :
				reqCmd.equals( Cmd.GET_ALL_BLOCK	.req() ) ? new GetBlockRespProcess 		< UDM > ( this ) :
				reqCmd.equals( Cmd.UPDATE			.req() ) ? new UpdateRespProcess 		< UDM > ( this ) :
				reqCmd.equals( Cmd.REMOVE			.req() ) ? new RemoveRespProcess 		< UDM > ( this ) : null;
//		 
//		return null;
	}
    
}
