package com.aladdindb.units;

import com.aladdindb.structure.sn.SnLineNavi;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class UnitsIdBlockNavi implements LineNavigator< String >  { 
	
	private final  SnLineNavi navi;
	
    public UnitsIdBlockNavi( SnPoint node ) {
    	this.navi = new SnLineNavi(node.children.snBottom.get() );
    }

	@Override
	public boolean hasRight() {
		return navi.hasRight();
	}

	@Override
	public boolean hasLeft() {
		return navi.hasLeft();
	}

	@Override
	public String right() {
		var node = navi.right();
		return node != null ? node.value.get() : null;
	}

	@Override
	public String left() {
		var node = navi.left();
		return node != null ? node.value.get() : null;
	}
    
}
