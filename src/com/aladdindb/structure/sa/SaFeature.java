package com.aladdindb.structure.sa;

import java.util.Objects;

import com.aladdindb.structure.sa.props.Sa;
import com.aladdindb.structure.sa.props.SaPoint;

/**
*
* @author Macit Kandemir
*/
public abstract class SaFeature  {
	
	public final SaPoint 	snPoint;
	public final Sa 		sa;
	
	public SaFeature( SaPoint saPoint  ) {
		Objects.requireNonNull( saPoint );
		
		this.snPoint 	= saPoint;
		this.sa 		= saPoint.getSA();
	}

}
