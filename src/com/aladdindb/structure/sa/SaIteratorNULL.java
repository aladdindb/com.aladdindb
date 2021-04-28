package com.aladdindb.structure.sa;

import java.util.Iterator;

import com.aladdindb.structure.sa.props.SaPoint;

/**
*
* @author Macit Kandemir
*/
public class SaIteratorNULL implements Iterable< SaPoint > {
	
	@Override
	public Iterator< SaPoint > iterator ( ) {
		return new Iterator< SaPoint > ( ) {
			
			@Override
			public boolean hasNext ( ) {
				return false;
			}
			
			@Override
			public SaPoint next ( ) {
				return null;
			}
			
			@Override
			public void remove ( ) {}
		};
	}
}
