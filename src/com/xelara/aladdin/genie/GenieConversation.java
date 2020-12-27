package com.xelara.aladdin.genie;

import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.model.WishModel;

public interface GenieConversation {
	
	public void begin( WishModel wishModel, Consumer< String > respConsumer );

}
