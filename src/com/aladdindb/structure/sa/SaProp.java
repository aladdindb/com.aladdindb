package com.aladdindb.structure.sa;

import java.util.Objects;

import com.aladdindb.structure.sa.props.Sa;
import com.aladdindb.structure.sa.props.SaPoint;

/**
*
* @author Macit Kandemir
*/
public abstract class SaProp extends SaFeature  {
	
	public SaProp( SaPoint saPoint ) {
		super( saPoint );
	}
	
    public final void set( SaPoint saPoint ) {
    	Objects.requireNonNull( saPoint );
    	this.setReal( saPoint.getSA() );
    }

    public final SaPoint get() {
    	var sa = this.getReal(); 
        return sa == null ? null : new SaPoint( sa );
    }
	
    public final boolean exist() {
        return this.getReal() != null;
    }
	
	protected abstract Sa 		getReal ();
	protected abstract void 	setReal ( Sa sa );

}
