package com.aladdindb.structure.sn;

import java.util.Iterator;
import java.util.Objects;

/**
*
* @author Macit Kandemir
*/
public class SnLineIteratorRightToLeft implements Iterator < SnPoint > { 
	
	private SnPoint node = null;
	
	public SnLineIteratorRightToLeft ( SnPoint node ) {
		Objects.requireNonNull( node );
		this.node = node.end.get();
	}
	
	@Override
	public boolean hasNext ( ) {
		return node != null;
	}
	
	@Override
	public SnPoint next ( ) {
		SnPoint rv = node;
		node = rv.left.get();
		return rv;
	}
	
	@Override
	public void remove ( ) {
		if ( node != null ) {
			SnPoint newNode = node.left.get();
			node.remove ( );
			node = newNode;
		}
	}
	
}
