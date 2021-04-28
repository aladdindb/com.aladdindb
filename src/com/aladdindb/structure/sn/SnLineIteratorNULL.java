package com.aladdindb.structure.sn;

import java.util.Iterator;

/**
*
* @author Macit Kandemir
*/
public class SnLineIteratorNULL implements Iterable < SnPoint > {
	
	@Override
	public Iterator<SnPoint> iterator() {
		return new Iterator<SnPoint>() {
			
			@Override
			public boolean hasNext() {
				return false;
			}
			
			@Override
			public SnPoint next() {
				return null;
			}
			
			@Override
			public void remove() {
			}
		};
	}
}
