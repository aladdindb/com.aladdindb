package com.aladdindb.finder;

public enum Type {

	LOGICAL_AND ("logical:and"),
	LOGICAL_OR	("logical:or"),
	LOGICAL_NOT	("logical:not"),
	
	LIST		("list");

	private final String tagName;
	
	private Type( String type ) {
		this.tagName = type;
	}
	
	public String finder() {
		return "finder:"+tagName;
	}

	public String sorter() {
		return "sorter:"+tagName;
	}
	
}
