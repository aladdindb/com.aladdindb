package com.aladdindb;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;

import com.aladdindb.method.Method;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.add.AddRespProcess;
import com.aladdindb.method.resp.close.method.session.CloseMethodSessionRespProcess;
import com.aladdindb.method.resp.get.GetAllRespProcess;
import com.aladdindb.method.resp.get.block.BlockNaviRespProcess;
import com.aladdindb.method.resp.get.by.id.GetByIdRespProcess;
import com.aladdindb.method.resp.remove.RemoveRespProcess;
import com.aladdindb.method.resp.search.SearchRespProcess;
import com.aladdindb.method.resp.update.UpdateRespProcess;
import com.aladdindb.store.Store;
import com.aladdindb.store.UnitIdBlockNavi;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 


	public final HashMap< String, UnitIdBlockNavi > unitsIdBlockNaviMap = new HashMap<>();
	
	public final Store	< UDM > 					store;
	
	public final Var 	< SnPoint > 				reqNode 		= new Var<>();
	public final Var 	< Consumer < String > > 	respConsumer 	= new Var<>();
	
	public final StoreSupport< UDM > support;
	
	
	public Genie( Path storePath, StoreSupport< UDM > support )  {
		System.out.println( "Store-Path :"+storePath );
		this.support 	= support;
		this.store		= new Store	< UDM > ( storePath, this.support.udmClass() );
	}
    
	
	@Override
	public void run() {
		this.reqNode.get( reqNode -> {
			
			RespProcess< UDM > process = this.createRespProcess( reqNode.key.get() );
			
			if( process != null ) process.run();
		});
	}
	
	private RespProcess< UDM > createRespProcess( String reqCmd ) {
		
		return 	  reqCmd.equals( Method.add				.store() ) ? new AddRespProcess 		< UDM > ( this )
				: reqCmd.equals( Method.getById			.store() ) ? new GetByIdRespProcess 	< UDM > ( this )
				: reqCmd.equals( Method.search			.store() ) ? new SearchRespProcess 		<     > ( this ) 
				: reqCmd.equals( Method.getAll			.store() ) ? new GetAllRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.getBlock		.store() ) ? new BlockNaviRespProcess 	< UDM > ( this ) 
				: reqCmd.equals( Method.update			.store() ) ? new UpdateRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.remove			.store() ) ? new RemoveRespProcess 		< UDM > ( this )
						
				: reqCmd.equals( Method.closeSession	.method() ) ? new CloseMethodSessionRespProcess < UDM > ( this )
						
				: null;
	}
    
}
