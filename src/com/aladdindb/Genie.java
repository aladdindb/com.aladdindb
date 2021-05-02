package com.aladdindb;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;

import com.aladdindb.finder.FinderSupplier;
import com.aladdindb.method.Method;
import com.aladdindb.method.resp.RespProcess;
import com.aladdindb.method.resp.add.AddRespProcess;
import com.aladdindb.method.resp.closemethodsession.CloseMethodSessionRespProcess;
import com.aladdindb.method.resp.get.GetAllRespProcess;
import com.aladdindb.method.resp.get.GetByFinderRespProcess;
import com.aladdindb.method.resp.get.block.BlockNaviRespProcess;
import com.aladdindb.method.resp.get.by.id.GetByIdRespProcess;
import com.aladdindb.method.resp.remove.RemoveRespProcess;
import com.aladdindb.method.resp.update.UpdateRespProcess;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.UnitsIdBlockNavi;
import com.aladdindb.units.Units;
import com.aladdindb.util.Var;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 


	public final HashMap< String, UnitsIdBlockNavi > unitsIdBlockNaviMap = new HashMap<>();
	
	public final	Units				< UDM > units;
	public final	DataTransformer 	< UDM > dataTransformer;

	public final 	FinderSupplier< UDM > finderSupplier;
	
	public final Var < SnPoint > 				reqNode 		= new Var<>();
	public final Var < Consumer < String > > 	respConsumer 	= new Var<>();
	
	
	public Genie( Path dbPath, DataTransformer < UDM > dataTransformer, FinderSupplier< UDM > finderSupplier )  {
		
		this.dataTransformer	= dataTransformer;
		this.finderSupplier 	= finderSupplier;
		
		this.units				= new Units	< UDM > ( dbPath, dataTransformer );
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
				: reqCmd.equals( Method.GET_BY_FINDER	.reqTagName() ) ? new GetByFinderRespProcess 	<     > ( this ) 
				: reqCmd.equals( Method.GET_ALL			.reqTagName() ) ? new GetAllRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.GET_BLOCK		.reqTagName() ) ? new BlockNaviRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.UPDATE			.reqTagName() ) ? new UpdateRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.REMOVE			.reqTagName() ) ? new RemoveRespProcess 		< UDM > ( this )
						
				: reqCmd.equals( Method.CLOSE_METHOD_SESSION.reqTagName() ) ? new CloseMethodSessionRespProcess < UDM > ( this )
						
				: null;
	}
    
}
