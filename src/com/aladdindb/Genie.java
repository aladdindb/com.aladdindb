package com.aladdindb;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.finder.FinderSupport;
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
import com.aladdindb.sorter.SorterSupport;
import com.aladdindb.store.Store;
import com.aladdindb.store.UnitIdBlockNavi;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 

	public final String 		storeId;
	public final Path 			storePath;
	public final Class < UDM > 	udmClass;
	
	public final HashMap< String, UnitIdBlockNavi > unitsIdBlockNaviMap = new HashMap<>();
	
	public final Store	< UDM > 					store;
	
	public final Var 	< SnPoint > 				reqNode 		= new Var<>();
	public final Var 	< Consumer < String > > 	respConsumer 	= new Var<>();
	
	private final FinderSupport< UDM > finderSupport; 
	private final SorterSupport< UDM > sorterSupport; 	
	
	
	public Genie( Class < UDM > udmClass, String storeId, Path storePath, FinderSupport< UDM > finderSupport, SorterSupport< UDM > sorterSupport   )  {
		System.out.println( "Store-Path :"+storePath );
		
		this.store			= new Store	< UDM > ( storePath, udmClass );
		
		this.storeId 		= storeId;
		this.storePath		= storePath;
		
		this.finderSupport = finderSupport;
		this.sorterSupport = sorterSupport;
		
		this.udmClass 		= udmClass;
	
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
				: reqCmd.equals( Method.search			.store() ) ? new SearchRespProcess 		<  	  > ( this, this.finderSupport, this.sorterSupport ) 
				: reqCmd.equals( Method.getAll			.store() ) ? new GetAllRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.getBlock		.store() ) ? new BlockNaviRespProcess 	< UDM > ( this ) 
				: reqCmd.equals( Method.update			.store() ) ? new UpdateRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.remove			.store() ) ? new RemoveRespProcess 		< UDM > ( this )
						
				: reqCmd.equals( Method.closeSession	.method() ) ? new CloseMethodSessionRespProcess < UDM > ( this )
						
				: null;
	}
    
	public static < UDM extends DataModel< UDM > > Genie< UDM > newGenie( Class < UDM > udmClass, String storeId, Path storePath, Function< Unit< UDM >, Var<?> >... functions ) {
		var fs = new FinderSupport<>( udmClass, functions );
		var ss = new SorterSupport<>( udmClass, functions );
		
		return new Genie<>(udmClass, storeId, storePath, fs, ss );
	}
}
