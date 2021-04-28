package com.aladdindb.structure.sn;

import com.aladdindb.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class SnLineNavi implements LineNavigator < SnPoint >  { 
	
	
    private SnPoint right	= null;
    private SnPoint left 	= null;
    
    public SnLineNavi( SnPoint snPoint ) {
    	this.right = snPoint.start.get();
    }
    
	public boolean hasRight() {
		return right != null;
	}

	public boolean hasLeft() {
		return left != null;
	}
	
	public SnPoint right() {
		if( this.hasRight() ) {
			SnPoint rv = this.right;
			this.right 	= rv.right.get();
			this.left 	= rv.left.get();
			return rv;
		}
		return null;
	}
	
	public SnPoint left() {
		if( this.hasLeft() ) {
			SnPoint rv = this.left;
			this.right 	= rv.right.get();
			this.left 	= rv.left.get();
			return rv;
		}
		return null;
	}
}
