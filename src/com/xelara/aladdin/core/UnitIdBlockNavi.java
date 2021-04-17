package com.xelara.aladdin.core;

import com.xelara.core.util.LineNavigator;
import com.xelara.structure.sn.SnLineNavi;
import com.xelara.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class UnitIdBlockNavi implements LineNavigator< String >  { 
	
	private final  SnLineNavi navi;
	
    public UnitIdBlockNavi( SnPoint snPoint ) {
    	this.navi = new SnLineNavi(snPoint.children.snBottom.get() );
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
		return node.value.get();
	}

	@Override
	public String left() {
		var node = navi.left();
		return node.value.get();
	}
    
}
