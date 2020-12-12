package com.xelara.aladdin.magiclamp;

public class MagicLampConnectionData {
	
	public final String	host;
	public final int	port;
	
	public final String	userID;
	
	public MagicLampConnectionData( String host, int port, String userID ) {
		
		this.host	= host;
		this.port	= port;
		this.userID	= userID;
	}

}
