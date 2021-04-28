package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.SnStringFeature;

/**
*
* @author Macit Kandemir
*/
public class SnValue extends SnStringFeature {
	
	
	public SnValue( SnPoint node  ) {
		super( node );
	}	
	
    @Override
    protected String getReal() {
    	return sn.value;
    }
    
    @Override
    protected void setReal( String value ) {
    	this.sn.value = value;
    }

}
