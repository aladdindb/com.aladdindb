package com.xelara.aladdin.model.simpleunit;

import com.xelara.aladdin.magiclamp.MagicLamp;
import com.xelara.aladdin.magiclamp.MagicLampConnectionData;
import com.xelara.aladdin.magiclamp.Monitor;
import com.xelara.aladdin.magiclamp.MonitorDefault;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitModel;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitModelParser;

public class SimpleUnitMagicLamp extends MagicLamp< SimpleUnitModel, SimpleUnitModelParser > {

	
	public SimpleUnitMagicLamp( String direction, MagicLampConnectionData connection ) {
		this ( direction, null, connection );
	}
	
	public SimpleUnitMagicLamp( String direction, Monitor monitor, MagicLampConnectionData connection ) {
		super( 
			new SimpleUnitModelParser(), 
			direction, 
			monitor != null ? monitor : new MonitorDefault(),
			connection 
		); 
	}
	
}
