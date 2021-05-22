package com.aladdindb.method;

public enum Method {
	
	add				("add"),
	getById			("getById"),
	search			("search"),
	getAll			("getAll"),
	getBlock		("getBlock"),
	update			("update"),
	remove			("remove"),
	
	closeSession 	("closeSession");
	
	private final String method;
	
	private Method( String method ) {
		this.method = method;
	}
	
	public String respTagName() {
		return "Resp."+method;
	}
	
	public String store() {
		return "Store."+method;
	}
	
	public String method() {
		return "Method."+method;
	}
	
}
