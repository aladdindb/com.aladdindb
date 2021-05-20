package com.aladdindb;

public enum Type {

	LOGICAL_AND ("list:logical:and"),
	LOGICAL_OR	("list:logical:or"),
	LOGICAL_NOT	("list:logical:not"),
	
	LIST		(":list");

	
	private final String realName;
	
	private Type( String realName ) {
		this.realName = realName;
	}
	
	public String finder() {
		return "finder:"+realName;
	}

	public String sorter() {
		return "sorter:"+realName;
	}
	
}
