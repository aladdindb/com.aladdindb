package com.xelara.aladdin.magiclamp.wishes;

import com.xelara.aladdin.magiclamp.Channel;
import com.xelara.aladdin.magiclamp.wishes.addunit.AddRequest;
import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.aladdin.unit.model.DataModelParser;

public class UnitsChannel < UGM extends DataModel < UGM > > {

    public final  Channel channel;

    public final DataModelParser < UGM >	unitGrpDataModelParser;
    public final String 									unitGroupID;

    
	private final AddRequest<UGM> addRequest = new AddRequest<>(this);
    
    //************************************************************
    //					
    //************************************************************

    public UnitsChannel( String unitGroupID, DataModelParser< UGM > dataModelParser, String host, int port  ) {
		this.unitGroupID 				= unitGroupID;
		this.unitGrpDataModelParser 	= dataModelParser;

		this.channel 					= new Channel(host, port);
    }
    
    public void sendRequest( String request ) {
    	this.channel.sendRequest(request);
    }
    
    //************************************************************
    //					
    //************************************************************
	
    public void addUnit( UGM dataModel ) {
		addRequest.process( dataModel, System.out :: println  );
    }
	
}
