package com.aladdindb.finder;

public enum FinnderType {

	LOGICAL_AND ("logical:and"),
	LOGICAL_OR	("logical:or"),
	LOGICAL_NOT	("logical:not");

	private final String tagName;
	
	private FinnderType( String type ) {
		this.tagName = type;
	}
	
	public String tagName() {
		return "finder:"+tagName;
	}
	
}
