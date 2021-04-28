package com.aladdindb;

import java.util.function.Consumer;

import com.aladdindb.filter.Filter;
import com.aladdindb.filter.FilterFactory;
import com.aladdindb.req.add.AddReqProcess;
import com.aladdindb.req.get.all.GetAllBlockNavi;
import com.aladdindb.req.get.by.filter.GetByFilterBlockNavi;
import com.aladdindb.req.get.by.filter.GetByFilterReqProcess;
import com.aladdindb.req.get.by.id.GetByIdReqProcess;
import com.aladdindb.req.remove.RemoveReqProcess;
import com.aladdindb.req.update.UpdateReqProcess;
import com.aladdindb.resp.add.AddRespModel;
import com.aladdindb.resp.get.by.id.GetByIdRespModel;
import com.aladdindb.resp.remove.RemoveResp;
import com.aladdindb.resp.update.UpdateResp;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitParser;

public class UnitsChannel < UDM extends DataModel < UDM > > {

	
    public final  Channel channel;

    
    public final DataParser < UDM >	unitDataParser;
    public final String 			unitGroupID;
    public final FilterFactory< UDM > filterFac;
    
    //************************************************************
    //					
    //************************************************************

    public UnitsChannel( String unitGroupID, DataParser< UDM > unitDataParser, FilterFactory< UDM > filterFac, String host, int port  ) {
		this.unitGroupID 		= unitGroupID;
		this.unitDataParser 	= unitDataParser;
		
		this.channel 			= new Channel( host, port );
		
		this.filterFac 			= filterFac;
    }
    
    public UnitParser< UDM > createUnitParser() {
    	return new UnitParser < UDM >( this.unitDataParser );
    }
    
    //************************************************************
    //					
    //************************************************************
	
    public void addUnit( UDM unitData, Consumer< AddRespModel > respConsumer ) {
    	var reqProcess = new AddReqProcess< UDM > ( unitData, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }

    public void getUnitByID( String unitID, Consumer< GetByIdRespModel< UDM > > respConsumer ) {
    	var reqProcess =  new GetByIdReqProcess< UDM > ( unitID, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }
    
    public void getAll( int blockSize, Consumer< GetAllBlockNavi< UDM > > consumer ) {
    	consumer.accept( new GetAllBlockNavi< UDM >(this, blockSize ));
    }

    public void getByFilter( int blockSize, Filter< UDM, ? extends Filter < UDM, ? > >  filter, Consumer< GetByFilterBlockNavi< UDM > > consumer ) {
    	consumer.accept( new GetByFilterBlockNavi<UDM>( this, blockSize, filter) );
    }
    
    public void updateUnit( Unit< UDM > unit, Consumer< UpdateResp > respConsumer  ) {
    	var reqProcess =  new UpdateReqProcess< UDM >( unit, this) ;
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }

    public void removeUnit( String unitID, Consumer < RemoveResp< UDM > > respConsumer ) {
    	var reqProcess =  new RemoveReqProcess< UDM > ( unitID, this );
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
