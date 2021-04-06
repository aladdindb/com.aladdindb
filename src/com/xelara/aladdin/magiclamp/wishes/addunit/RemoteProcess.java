package com.xelara.aladdin.magiclamp.wishes.addunit;

import java.util.function.Consumer;

import com.xelara.structure.sn.SnPoint;

public interface RemoteProcess {
	
	public void process(  SnPoint cmdReqSnPoint, Consumer < String > respConsumer  );

}
