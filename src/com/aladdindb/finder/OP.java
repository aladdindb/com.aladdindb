package com.aladdindb.finder;

public enum OP {
	
	MATCHES					("matches"),
	EQUAL					("=="), 

	GREATER 				(">"), 
	GREATER_OR_EQUAL		(">="), 

	LESS					("<"),
	LESS_OR_EQUAL			("<="),
	
	//   Not
	
	NOT_MATCHES				("!matches"),
	NOT_EQUAL				("!="), 

	NOT_GREATER 			("!>"), 
	NOT_GREATER_OR_EQUAL	("!>="),
	
	NOT_LESS				("!<"),
	NOT_LESS_OR_EQUAL		("!<=");
	
	
	
	
	private String real;
	
	private OP( String real ) {
		this.real = real;
	}
	
	public String real() {
		return real;
	}
	
	
}
