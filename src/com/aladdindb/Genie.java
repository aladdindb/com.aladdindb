package com.aladdindb;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Consumer;

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
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.Units;
import com.aladdindb.units.UnitsIdBlockNavi;
import com.aladdindb.util.Var;


public class Genie < UDM extends DataModel < UDM > > implements Runnable { 


	public final HashMap< String, UnitsIdBlockNavi > unitsIdBlockNaviMap = new HashMap<>();
	
	public final	Units			< UDM > units;
	public final	Transformer 	< UDM > dataTransformer;

	public final 	FinderSupport 	< UDM > finderSupport;
	public final 	SorterSupport 	< UDM > sorterSupport;
	
	public final Var < SnPoint > 				reqNode 		= new Var<>();
	public final Var < Consumer < String > > 	respConsumer 	= new Var<>();
	
	
	public Genie( Path dbPath, Transformer < UDM > dataTransformer, FinderSupport< UDM > finderSupport, SorterSupport< UDM > sorterSupport )  {
		
		this.dataTransformer	= dataTransformer;
		this.finderSupport 		= finderSupport;
		this.sorterSupport		= sorterSupport;
		
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
				: reqCmd.equals( Method.SEARCH			.reqTagName() ) ? new SearchRespProcess 		<     > ( this ) 
				: reqCmd.equals( Method.GET_ALL			.reqTagName() ) ? new GetAllRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.GET_BLOCK		.reqTagName() ) ? new BlockNaviRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.UPDATE			.reqTagName() ) ? new UpdateRespProcess 		< UDM > ( this ) 
				: reqCmd.equals( Method.REMOVE			.reqTagName() ) ? new RemoveRespProcess 		< UDM > ( this )
						
				: reqCmd.equals( Method.CLOSE_METHOD_SESSION.reqTagName() ) ? new CloseMethodSessionRespProcess < UDM > ( this )
						
				: null;
	}
    
}
