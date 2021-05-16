package com.aladdindb;

import java.util.function.Consumer;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupport;
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
import com.aladdindb.sorter.SorterSupport;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitTransformer;


public class MagicLamp < UDM extends Store < UDM > > {

	
    public final  GenieConnection genieConnection;

    
    public final String 					unitGroupID;
    public final Transformer		< UDM >	unitDataTransformer;
    
    public final FinderSupport		< UDM > finderSupport;
    public final SorterSupport		< UDM > sorterSupport;
    
    //************************************************************
    //					
    //************************************************************

    public MagicLamp( 	String unitGroupID, 
    					Transformer		< UDM > unitDataTransformer, 
    					FinderSupport	< UDM > finderSupport, 
    					SorterSupport	< UDM > sorterSupport, 
    					GenieConnection genieConnection ) {
    	
		this.unitGroupID 			= unitGroupID;
		this.unitDataTransformer 	= unitDataTransformer;
		this.finderSupport 			= finderSupport;
		this.sorterSupport 			= sorterSupport;
		
		this.genieConnection 		= genieConnection;
		
    }
    
    public UnitTransformer< UDM > createUnitParser() {
    	return new UnitTransformer < UDM >( this.unitDataTransformer );
    }
    
    //************************************************************
    //					
    //************************************************************
	
    public void add( UDM unitData, Consumer< AddResp > respConsumer ) {
    	var reqProcess = new AddReqProcess< UDM > ( unitData, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }

    public void getByID( String unitID, Consumer< GetByIdResp< UDM > > respConsumer ) {
    	var reqProcess =  new GetByIdReqProcess< UDM > ( unitID, this );
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

    public void remove( String unitID, Consumer < RemoveResp< UDM > > respConsumer ) {
    	var reqProcess =  new RemoveReqProcess< UDM > ( unitID, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    } 
    
    public void closeMethodSession( String methodSessionID, Consumer< CloseMethodSessionResp > respConsumer ) {
    	var reqProcess = new CloseMethodSessionReqProcess< UDM >( methodSessionID,  this );
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
