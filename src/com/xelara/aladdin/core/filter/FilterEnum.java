package com.xelara.aladdin.core.filter;

public enum FilterEnum {

	FILTER_LOGICAL_AND 	("filter:logical:operations:and"),
	FILTER_LOGICAL_OR	("filter:logical:operations:or");

	private final String cmd;
	
	private FilterEnum( String cmd ) {
		this.cmd = cmd;
	}
	
	public String cmd() {
		return cmd;
	}
	
}
