package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.SnProp;

/**
*
* @author Macit Kandemir
*/
public class SnRight extends SnProp {
	
	
	public SnRight( SnPoint node  ) {
		super( node );
	}	
	
    @Override
    protected Sn getReal() {
    	return sn.right;
    }
    
    @Override
    protected void setReal( Sn sn ) {
    	this.sn.right = sn;
    }

}
