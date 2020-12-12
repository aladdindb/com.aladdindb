package com.xelara.aladdin.magiclamp;

public class MonitorDefault implements Monitor {

	@Override
	public void monitoring( Runnable runnable ) {
		runnable.run();
	}

}
