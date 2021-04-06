package com.xelara.aladdin.magiclamp;

public class Connection {
	
    private static Channel socket = null;
	
	
	public final String	host;
	public final int	port;
	
	public final String	userID;
	
	public Connection( String host, int port, String userID ) {
		
		this.host	= host;
		this.port	= port;
		this.userID	= userID;
	}

}
