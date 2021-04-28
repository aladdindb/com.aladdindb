package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.SnStringFeature;

/**
*
* @author Macit Kandemir
*/
public class SnKey extends SnStringFeature {
	
	
	public SnKey( SnPoint node  ) {
		super( node );
	}	
	
    @Override
    protected String getReal() {
    	return sn.key;
    }
    
    @Override
    protected void setReal( String key ) {
    	this.sn.key = key;
    }

}
