package com.aladdindb.structure.sa.props;

import com.aladdindb.structure.sa.SaProp;

/**
*
* @author Macit Kandemir
*/
public class SaLeft extends SaProp {
	
	
	public SaLeft( SaPoint sAttribute  ) {
		super( sAttribute );
	}	
	
    @Override
    protected Sa getReal() {
    	return sa.left;
    }
    
    @Override
    protected void setReal( Sa sa ) {
    	this.sa.left = sa;
    }

}
