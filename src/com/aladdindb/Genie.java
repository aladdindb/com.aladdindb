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
	
	public final Support< UDM > support;
	
	
	public Genie( Path storePath, Support< UDM > support )  {
		System.out.println( "Store-Path :"+storePath );
		this.support 	= support;
		this.store		= new Store	< UDM > ( storePath, this.support.newTransformer() );
	}
    
	
	@Override
	public void run() {
		this.reqNode.get( reqNode -> {
			
			RespProcess< UDM > process = this.createRespProcess( reqNode.key.get() );
			
			if( process != null ) process.run();
		});
	}
	
	private RespProcess< UDM > createRespProcess( String reqCmd ) {
		
		return 	  reqCmd.equals( Method.ADD				.reqTagName() ) ? new AddRespProcess 			< UDM > ( this )
				: reqCmd.equals( Method.GET_BY_ID		.reqTagName() ) ? new GetByIdRespProcess 		< UDM > ( this )
				: reqCmd.equals( Method.SEARCH			.reqTagName() ) ? new SearchRespProcess 		<     > ( this ) 
				: reqCmd.equals( Method.GET_ALL			.reqTagName() ) ? new GetAllRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.GET_BLOCK		.reqTagName() ) ? new BlockNaviRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.UPDATE			.reqTagName() ) ? new UpdateRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.REMOVE			.reqTagName() ) ? new RemoveRespProcess 		< UDM > ( this )
						
				: reqCmd.equals( Method.CLOSE_METHOD_SESSION.reqTagName() ) ? new CloseMethodSessionRespProcess < UDM > ( this )
						
				: null;
	}
    
}
