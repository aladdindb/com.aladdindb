package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.SnProp;

/**
*
* @author Macit Kandemir
*/
public class SnLeft extends SnProp {
	
	
	public SnLeft( SnPoint node  ) {
		super( node );
	}	
	
    @Override
    protected Sn getReal() {
    	return sn.left;
    }
    
    @Override
    protected void setReal( Sn sn ) {
    	this.sn.left = sn;
    }

}
