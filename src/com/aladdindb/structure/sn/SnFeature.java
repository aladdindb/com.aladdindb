package com.aladdindb.structure.sn;

import java.util.Objects;

import com.aladdindb.structure.sn.props.Sn;

/**
*
* @author Macit Kandemir
*/
public abstract class SnFeature {
	
	public final SnPoint 	node;
	public final Sn 		sn;
	
	public SnFeature( SnPoint snPoint  ) {
		Objects.requireNonNull( snPoint );
		
		this.node 	= snPoint;
		this.sn 	= snPoint.getSN();
	}

}
