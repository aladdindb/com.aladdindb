package com.aladdindb;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;

import com.aladdindb.filter.Filter;
import com.aladdindb.filter.FilterFactory;
import com.aladdindb.method.Method;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.add.AddRespProcess;
import com.aladdindb.method.resp.get.GetAllRespProcess;
import com.aladdindb.method.resp.get.GetByFilterRespProcess;
import com.aladdindb.method.resp.get.block.GetBlockRespProcess;
import com.aladdindb.method.resp.get.by.id.GetByIdRespProcess;
import com.aladdindb.method.resp.remove.RemoveRespProcess;
import com.aladdindb.method.resp.update.UpdateRespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.UnitIdBlockNavi;
import com.aladdindb.units.Units;
import com.aladdindb.util.Var;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 


	public final HashMap< String, UnitIdBlockNavi > unitIdBlockNaviMap = new HashMap<>();
	
	public final	Units		< UDM > units;
	public final	DataParser 	< UDM > dataParser;

	public final 	FilterFactory< UDM > filterFac;
	
	public final Var < SnPoint > 				reqNode 		= new Var<>();
	public final Var < Consumer < String > > 	respConsumer 	= new Var<>();
	
	
	public Genie( Path dbPath, DataParser < UDM > dataParser, FilterFactory< UDM > filterFac ) throws IOException {
		
		this.dataParser		= dataParser;
		this.filterFac 		= filterFac;
		
		this.units			= new Units	< UDM > ( dbPath, dataParser );
	}
    
	@Override
	public void run() {
		this.reqNode.get( reqNode -> {
			
			RespProcess< UDM > process = this.createRespProcess( reqNode.key.get() );
			
			if( process != null ) process.run();
		});
	}
	
	private RespProcess< UDM > createRespProcess( String reqCmd ) {  
		
		return 	  reqCmd.equals( Method.ADD				.req() ) ? new AddRespProcess 			< UDM > ( this )
				: reqCmd.equals( Method.GET_BY_ID		.req() ) ? new GetByIdRespProcess 		< UDM > ( this )
				: reqCmd.equals( Method.GET_BY_FILTER	.req() ) ? new GetByFilterRespProcess 	<     > ( this ) 
				: reqCmd.equals( Method.GET_ALL			.req() ) ? new GetAllRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.GET_ALL_BLOCK	.req() ) ? new GetBlockRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.UPDATE			.req() ) ? new UpdateRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.REMOVE			.req() ) ? new RemoveRespProcess 		< UDM > ( this )  
				: null;
	}
    
}
