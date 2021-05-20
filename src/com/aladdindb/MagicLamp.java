package com.aladdindb;

import java.util.function.Consumer;

import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.add.AddReqProcess;
import com.aladdindb.method.req.close.method.session.CloseMethodSessionReqProcess;
import com.aladdindb.method.req.get.all.GetAllReqProcess;
import com.aladdindb.method.req.get.by.id.GetByIdReqProcess;
import com.aladdindb.method.req.remove.RemoveReqProcess;
import com.aladdindb.method.req.search.SearchReqProcess;
import com.aladdindb.method.req.update.UpdateReqProcess;
import com.aladdindb.method.resp.add.AddResp;
import com.aladdindb.method.resp.close.method.session.CloseMethodSessionResp;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.by.id.GetByIdResp;
import com.aladdindb.method.resp.remove.RemoveResp;
import com.aladdindb.method.resp.update.UpdateResp;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;


public class MagicLamp < UDM extends DataModel < UDM > > {

	
    public final  GenieConnection genieConnection;

    public final Support < UDM > support;
    
    //************************************************************
    //					
    //************************************************************

    public MagicLamp( Support< UDM > support, GenieConnection genieConnection ) {
    	
    	this.support 				= support;
		this.genieConnection 		= genieConnection;
		
    }
    
    //************************************************************
    //					
    //************************************************************
	
    public void add( UDM data, Consumer< AddResp > respConsumer ) {
    	var reqProcess = new AddReqProcess< UDM > ( data, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }

    public void getById( String unitId, Consumer< GetByIdResp< UDM > > respConsumer ) {
    	var reqProcess =  new GetByIdReqProcess< UDM > ( unitId, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }
    
    public void getAll( int blockSize, Consumer< BlockNavResp > respConsumer ) {
    	var reqProcess = new GetAllReqProcess<>( blockSize,  this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }

    public void search( int blockSize, 
    					Finder 		< UDM, ? extends Finder < UDM, ? > >  	finder, 
    					Sorter 		< UDM, ? extends Sorter < UDM, ? > >  	sorter, 
    					Consumer 	< BlockNavResp > 						respConsumer ) {
    	var reqProcess = new SearchReqProcess( blockSize, finder, sorter, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }
    
    public void update( Unit < UDM > unit, Consumer < UpdateResp > respConsumer  ) {
    	var reqProcess =  new UpdateReqProcess< UDM >( unit, this) ;
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }

    public void remove( String unitId, Consumer < RemoveResp< UDM > > respConsumer ) {
    	var reqProcess =  new RemoveReqProcess< UDM > ( unitId, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    } 
    
    public void closeMethodSession( String methodSessionId, Consumer< CloseMethodSessionResp > respConsumer ) {
    	var reqProcess = new CloseMethodSessionReqProcess< UDM >( methodSessionId,  this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }
    
    //************************************************************
    //					
    //************************************************************
    
//    public Map< String, String > createIdLabelMap() {
//        Map< String, String > rv = new HashMap<>();
//        var wish = createWish(  GET_ALL, "" );
//        forEachUnit( wish, unitNode -> {
//            var unitID      = unitNode.attributes.getValue( "id"       );
//            var unitLabel 	= unitNode.attributes.getValue( "label"    );
//            rv.put( unitID, unitLabel );
//        });
//        return rv;
//    }
    
}
