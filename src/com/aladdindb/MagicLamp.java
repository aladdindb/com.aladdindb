package com.aladdindb;

import java.util.function.Consumer;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupplier;
import com.aladdindb.method.req.add.AddReqProcess;
import com.aladdindb.method.req.closemethodsession.CloseMethodSessionReqProcess;
import com.aladdindb.method.req.get.all.GetAllReqProcess;
import com.aladdindb.method.req.get.by.finder.GetByFinderReqProcess;
import com.aladdindb.method.req.get.by.id.GetByIdReqProcess;
import com.aladdindb.method.req.remove.RemoveReqProcess;
import com.aladdindb.method.req.update.UpdateReqProcess;
import com.aladdindb.method.resp.add.AddResp;
import com.aladdindb.method.resp.closemethodsession.CloseMethodSessionResp;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.method.resp.get.by.id.GetByIdResp;
import com.aladdindb.method.resp.remove.RemoveResp;
import com.aladdindb.method.resp.update.UpdateResp;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitParser;


public class MagicLamp < UDM extends DataModel < UDM > > {

	
    public final  GenieConnection genieConnection;

    
    public final String 					unitGroupID;
    public final DataTransformer	< UDM >	unitDataTransformer;
    public final FinderSupplier		< UDM > finderSupplier;
    
    //************************************************************
    //					
    //************************************************************

    public MagicLamp( String unitGroupID, DataTransformer< UDM > unitDataTransformer, FinderSupplier< UDM > finderSupplier, GenieConnection genieConnection ) {
    	
		this.unitGroupID 			= unitGroupID;
		this.unitDataTransformer 	= unitDataTransformer;
		this.finderSupplier 		= finderSupplier;
		
		this.genieConnection 		= genieConnection;
		
    }
    
    public UnitParser< UDM > createUnitParser() {
    	return new UnitParser < UDM >( this.unitDataTransformer );
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

    public void getByFinder( int blockSize, Finder < UDM, ? extends Finder >  finder, Consumer < BlockNavResp > respConsumer ) {
    	var reqProcess = new GetByFinderReqProcess( blockSize, finder, this );
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
