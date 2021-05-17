package com.aladdindb;

public enum Type {

	LOGICAL_AND (":list:logical:and"),
	LOGICAL_OR	(":list:logical:or"),
	LOGICAL_NOT	(":list:logical:not"),
	
	LIST		(":list");

	
	private final String tagName;
	
	private Type( String type ) {
		this.tagName = type;
	}
	
	public String finder() {
		return "finder"+tagName;
	}

	public String sorter() {
		return "sorter"+tagName;
	}
	
}
