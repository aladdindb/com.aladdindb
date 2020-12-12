package com.xelara.aladdin.model.simpleunit;


import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.genie.Genie;
import com.xelara.aladdin.index.IndexOrigin;
import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitListModel;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitListModelParser;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitModel;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitModelParser;

public class SimpleUnitGenie extends Genie

	< SimpleUnitModel, SimpleUnitModelParser, SimpleUnitListModel, SimpleUnitListModelParser, SimpleUnit > {
	
	public SimpleUnitGenie( Path dbPath, WishModel wishModel, Consumer < String > respConsumer ) {
		this( dbPath, wishModel, null, respConsumer );
	}
	
	public SimpleUnitGenie( Path dbPath, WishModel wishModel, IndexOrigin indexes, Consumer < String > respConsumer ) {
		super(  wishModel, 
			new SimpleUnitModelParser(),
			new SimpleUnitListModel(),
			new SimpleUnitListModelParser(),
			new SimpleUnit( dbPath, wishModel, indexes ),
			respConsumer );
	}
	
    
}
