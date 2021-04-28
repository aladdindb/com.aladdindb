package com.aladdindb.structure.sa.props;

import com.aladdindb.structure.sa.SaProp;

/**
*
* @author Macit Kandemir
*/
public class SaRight extends SaProp {
	
	
	public SaRight( SaPoint sAttribute  ) {
		super( sAttribute );
	}	
	
    @Override
    protected Sa getReal() {
    	return sa.right;
    }
    
    @Override
    protected void setReal( Sa sa ) {
    	this.sa.right = sa;
    }

}
