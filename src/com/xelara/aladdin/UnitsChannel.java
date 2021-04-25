package com.xelara.aladdin;

import java.util.function.Consumer;

import com.xelara.aladdin.core.UnitRemoteBlockNavi;
import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.core.filter.FilterFactory;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.req.add.AddReqProcess;
import com.xelara.aladdin.req.get.byid.GetByIdReqProcess;
import com.xelara.aladdin.req.get.filtered.GetFilteredReqProcess;
import com.xelara.aladdin.req.remove.RemoveReqProcess;
import com.xelara.aladdin.req.update.UpdateReqProcess;
import com.xelara.aladdin.resp.add.AddResp;
import com.xelara.aladdin.resp.get.byid.GetByIdResp;
import com.xelara.aladdin.resp.remove.RemoveResp;
import com.xelara.aladdin.resp.update.UpdateResp;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;

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
	
    public void addUnit( UDM unitData, Consumer< AddResp > respConsumer ) {
    	var reqProcess = new AddReqProcess< UDM > ( unitData, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }

    public void getUnitByID( String unitID, Consumer< GetByIdResp< UDM > > respConsumer ) {
    	var reqProcess =  new GetByIdReqProcess< UDM > ( unitID, this );
    	reqProcess.respConsumer.set( respConsumer );
    	reqProcess.run();
    }
    
    public void getAll( int blockSize, Consumer< UnitRemoteBlockNavi< UDM > > consumer ) {
    	consumer.accept( new UnitRemoteBlockNavi< UDM >(this, blockSize ));
    }

    public void getByFilter( int blockSize, Filter  filter, Consumer< UnitRemoteBlockNavi< UDM > > consumer ) {
    	var a = new GetFilteredReqProcess<>( blockSize, filter, this);
    	a.run();
    			
//    	var x = new GetFilteredReqProcess< UDM, ? extends Filter<UDM, ? > > (blockSize,  filter ,  this);
//    	consumer.accept( new UnitRemoteBlockNavi< UDM >(this, blockSize ));
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
