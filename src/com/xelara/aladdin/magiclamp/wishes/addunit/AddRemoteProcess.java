package com.xelara.aladdin.magiclamp.wishes.addunit;

import java.util.function.Consumer;

import com.xelara.aladdin.genie.Genie;
import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.structure.sn.SnPoint;

public class AddRemoteProcess < UGM extends DataModel < UGM > > implements RemoteProcess { 


	private final Genie	< UGM > genie;

	
	public AddRemoteProcess( Genie < UGM > genie ) { 
		this.genie = genie;
	}
	
    //************************************************************
    //					
    //************************************************************

	@Override
	public void process( SnPoint cmdReqSnPoint, Consumer < String > respConsumer  ) {
		new AddModelParser < UGM > ( genie.dataModelParser ).toModel(cmdReqSnPoint, model -> {
			model.data.get( dataModel -> {
				String newID = genie.units.addUnit( dataModel ) ;
				respConsumer.accept( newID );
			});
		});
	}
	
    //************************************************************
    //					
    //************************************************************
	
}
