package com.aladdindb.structure.sa.props;

import com.aladdindb.structure.sa.SaStringFeature;

/**
*
* @author Macit Kandemir
*/
public class SaKey extends SaStringFeature {
	
	
	public SaKey( SaPoint sAttribute  ) {
		super( sAttribute );
	}	
	
    @Override
    protected String getReal() {
    	return sa.key;
    }
    
    @Override
    protected void setReal( String key ) {
    	this.sa.key = key;
    }

}
