package com.aladdindb.structure.sa;

import java.util.Iterator;

import com.aladdindb.structure.sa.props.SaPoint;

/**
*
* @author Macit Kandemir
*/
public class SaIteratorLeftToRight implements Iterator< SaPoint > {
	
	private SaPoint sAttribute = null;
	
	public SaIteratorLeftToRight (SaPoint sAttribute) {
		this.sAttribute = sAttribute.getOwner().attributes.snAttribute.get();
	}
	
	@Override
	public boolean hasNext ( ) {
		return this.sAttribute != null;
	}
	
	@Override
	public SaPoint next ( ) {
		SaPoint rv = this.sAttribute;
		if( rv != null)this.sAttribute = rv.right.get();
		return rv;
	}
	
	@Override
	public void remove ( ) {
		if ( this.sAttribute != null ) {
			SaPoint right = this.sAttribute.right.get();
			this.sAttribute.remove();
			this.sAttribute = right;
		}
	}
	
}
