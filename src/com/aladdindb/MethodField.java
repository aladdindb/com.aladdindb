package com.aladdindb;

public enum MethodField {

	LOGICAL_AND ( ":logicalAnd"	),
	LOGICAL_OR	( ":logicalOr"	),
	LOGICAL_NOT	( ":logicalNot"	),
	
	LIST		("");

	
	private final String realName;
	
	private MethodField( String realName ) {
		this.realName = realName;
	}
	
//	public String realName() {
//		return realName;
//	}
	
	public String asFinderList() {
		return "Finder:List"+realName;
	}
 
	public String asSorterList() {
		return "Sorter:List"+realName;
	}
}
