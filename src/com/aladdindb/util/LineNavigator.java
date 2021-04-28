package com.aladdindb.util;

public interface LineNavigator < T > {
	
	enum DIRECTION { left, right }
	
	public boolean 	hasRight();
	public boolean 	hasLeft();
	public T 		right();
	public T 		left();
	
}

