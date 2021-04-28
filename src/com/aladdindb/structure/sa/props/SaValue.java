package com.aladdindb.structure.sa.props;

import com.aladdindb.structure.sa.SaStringFeature;

/**
*
* @author Macit Kandemir
*/
public class SaValue extends SaStringFeature {
	
	
	public SaValue( SaPoint snPoint  ) {
		super( snPoint );
	}	
	
    @Override
    protected String getReal() {
    	return sa.value;
    }
    
    @Override
    protected void setReal( String value ) {
    	this.sa.value = value;
    }

}
