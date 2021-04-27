package com.xelara.aladdin.req;

public enum Cmd {
	
	ADD				("ADD"),
	GET_BY_ID		("get:by:id"),
	GET_BY_FILTER	("get:by:filter"),
	GET_ALL			("get:all"),
	GET_ALL_BLOCK	("get:all:block"),
	UPDATE			("update"),
	REMOVE			("remove");
	
	private final String cmd;
	
	private Cmd( String cmd ) {
		this.cmd = cmd;
	}
	
	public String res() {
		return "resp:"+cmd;
	}
	
	public String req() {
		return "req:"+cmd;
	}
	
	
}
