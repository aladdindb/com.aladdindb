package com.aladdindb.method;

public enum Method {
	
	ADD				("add"),
	GET_BY_ID		("get:by:id"),
	SEARCH			("search"),
	GET_ALL			("get:all"),
	GET_BLOCK		("get:block"),
	UPDATE			("update"),
	REMOVE			("remove"),
	
	CLOSE_METHOD_SESSION ("close:method.session");
	
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
