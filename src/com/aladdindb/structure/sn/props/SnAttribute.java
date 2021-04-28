package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sa.props.SaPoint;
import com.aladdindb.structure.sn.SnFeature;
import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SnAttribute extends SnFeature  {
	
	public SnAttribute( SnPoint saPoint  ) {
		super( saPoint );
	}	
	
    public SaPoint get() {
    	return sn.attribute != null ? new SaPoint( sn.attribute ) : null;
    }
    
    public void set( SaPoint sn ) {
    	this.sn.attribute = sn.getSA();
    	sn.setOwner( node );
    }

}
