package com.xelara.aladdin.client;

import java.util.function.Consumer;

import com.xelara.aladdin.client.req.add.AddReqProcess;
import com.xelara.aladdin.client.req.get.all.GetAllReqProcess;
import com.xelara.aladdin.client.req.get.byid.GetByIdReqProcess;
import com.xelara.aladdin.client.req.remove.RemoveReqProcess;
import com.xelara.aladdin.client.req.update.UpdateReqProcess;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.core.UnitRemoteBlockNavi;
import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.aladdin.server.resp.get.all.block.GetAllBlockResp;

public class UnitsChannel < UDM extends DataModel < UDM > > {

    public final  Channel channel;

    public final DataParser < UDM >	unitDataParser;
    public final String 			unitGroupID;

    
    //************************************************************
    //					
    //************************************************************

    public UnitsChannel( String unitGroupID, DataParser< UDM > unitDataParser, String host, int port  ) {
		this.unitGroupID 		= unitGroupID;
		this.unitDataParser 	= unitDataParser;
		
		this.channel 			= new Channel( host, port );
    }
    
    //************************************************************
    //					
    //************************************************************
	
    public void addUnit( UDM unitData, Consumer< String > unitIdConsumer ) {
    	var reqProcess = new AddReqProcess < UDM > ( unitData, this );
    	reqProcess.respConsumer.set( unitIdConsumer );
    	reqProcess.run();
    }

    public void getUnitByID( String unitID, Consumer< Unit<UDM >> unitConsumer ) {
    	var reqProcess =  new GetByIdReqProcess < UDM > ( unitID, this );
    	reqProcess.respConsumer.set( unitConsumer );
    	reqProcess.run();
    }
    
    public void getAll( int blockSize, Consumer< UnitRemoteBlockNavi< UDM > > consumer ) {
    	consumer.accept( new UnitRemoteBlockNavi< UDM >(this, blockSize ));
    }
    
    public void updateUnit( Unit< UDM > unit, Consumer< String > unitIdConsumer  ) {
    	var reqProcess =  new UpdateReqProcess < UDM >( unit, this) ;
    	reqProcess.respConsumer.set( unitIdConsumer );
    	reqProcess.run();
    }

    public void removeUnit( String unitID, Consumer < Unit < UDM > > unitConsumer ) {
    	var reqProcess =  new RemoveReqProcess < UDM > ( unitID, this );
    	reqProcess.respConsumer.set( unitConsumer );
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
