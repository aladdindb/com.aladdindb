package com.aladdindb.method;

public enum Method {
	
	ADD				("add"),
	GET_BY_ID		("get:by:id"),
	GET_BY_FILTER	("get:by:filter"),
	GET_ALL			("get:all"),
	GET_ALL_BLOCK	("get:all:block"),
	UPDATE			("update"),
	REMOVE			("remove");
	
	private final String method;
	
	private Method( String method ) {
		this.method = method;
	}
	
	public String respTagName() {
		return "resp:"+method;
	}
	
	public String reqTagName() {
		return "req:"+method;
	}
	
	
}
