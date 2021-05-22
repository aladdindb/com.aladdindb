package com.aladdindb.finder;

public enum OP {
	
	matches					("matches"),
	equal					("=="), 

	greater 				(">"), 
	greaterOrEqual			(">="), 

	less					("<"),
	lessOrEqual				("<="),
	
	//   Not
	
	notMatches				("!matches"),
	notEqual				("!="), 

	notGreater 				("!>"), 
	notGreaterOrEqual		("!>="),
	
	notLess					("!<"),
	notLessOrEqual			("!<=");
	
	
	private String real;
	
	private OP( String real ) {
		this.real = real;
	}
	
	public String real() {
		return real;
	}
	
	
}
