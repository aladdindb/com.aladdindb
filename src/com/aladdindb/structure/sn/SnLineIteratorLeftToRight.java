package com.aladdindb.structure.sn;

import java.util.Iterator;
import java.util.Objects;

/**
*
* @author Macit Kandemir
*/
public class SnLineIteratorLeftToRight implements Iterator < SnPoint > { 
	
	
    private SnPoint snPoint = null;

    public SnLineIteratorLeftToRight( SnPoint snPoint ) {
    	Objects.requireNonNull( snPoint );
    	this.snPoint = snPoint.start.get();
    }
    
	@Override
	public boolean hasNext() {
		return snPoint != null;
	}
	
	@Override
	public SnPoint next() {
		SnPoint rv = snPoint;
		snPoint = rv.right.get();
		return rv;
	}
	
	@Override
	public void remove() {
		if( this.snPoint != null ) {
			SnPoint newSnPoint = this.snPoint.right.get();
			this.snPoint.remove();
			this.snPoint = newSnPoint;
		}
	}
	
}
