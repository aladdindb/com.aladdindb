package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.SnProp;

/**
*
* @author Macit Kandemir
*/
public class SnBottom extends SnProp {
	
	
	public SnBottom( SnPoint snPoint  ) {
		super( snPoint );
	}	
	
    @Override
    protected Sn getReal() {
    	return this.sn.bottom;
    }
    
    @Override
    protected void setReal( Sn sn ) {
    	if( sn != null ) {
        	this.sn.bottom = sn;
        	sn.top = this.sn;
    	} else this.sn.bottom = null;
    }

}
