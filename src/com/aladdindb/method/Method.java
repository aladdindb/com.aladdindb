package com.aladdindb.method;

public enum Method {
	
	ADD				("add"),
	GET_BY_ID		("getByID"),
	SEARCH			("search"),
	GET_ALL			("getAll"),
	GET_BLOCK		("getBlock"),
	UPDATE			("update"),
	REMOVE			("remove"),
	
	CLOSE_METHOD_SESSION ("closeMethodSession");
	
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
