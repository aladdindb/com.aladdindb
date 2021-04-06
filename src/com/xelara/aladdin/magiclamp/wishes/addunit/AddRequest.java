package com.xelara.aladdin.magiclamp.wishes.addunit;

import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.wishes.UnitsChannel;
import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.structure.xml.XML;

public class AddRequest < UGM extends DataModel < UGM > > { 


	private final UnitsChannel< UGM > unitsChannel;
	
	
	public AddRequest( UnitsChannel< UGM > unitsChannel ) {
		this.unitsChannel = unitsChannel;
	}
	
    //************************************************************
    //					
    //************************************************************

	public void process( UGM dataModel, Consumer<String> respConsumer) {
		
		var addModel 		= new AddModel			<UGM> ( unitsChannel.unitGroupID, null, dataModel );
		var addModelParser 	= new AddModelParser	<UGM> ( unitsChannel.unitGrpDataModelParser );
		
		addModelParser.toNode( addModel, cmdReqSnPoint -> {
			XML.parse( cmdReqSnPoint, cmdReqStr -> {
				System.out.println("Magic-Lamp:");
				System.out.println( cmdReqStr );
//				String resp = unitsChannel.sendRequest( wishStr ); 
				unitsChannel.sendRequest( cmdReqStr ); 
//				if( resp != null )respConsumer.accept(resp);
			});
		});
		
	}
	
	
}
