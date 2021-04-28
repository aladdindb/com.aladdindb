package com.aladdindb.structure.sn;

import java.util.Objects;


/**
*
* @author Macit Kandemir
*/
public abstract class SnByteFeature extends SnFeature  {
	
	public SnByteFeature( SnPoint node  ) {
		super( node );
	}
	
    public final void set( Byte value ) {
    	Objects.requireNonNull( value );
    	this.setReal( value );
    }

    public final Byte get() {
    	return this.getReal(); 
    }
	
    public final boolean exist() {
    	return this.get() != null;
    }
	
	protected abstract byte 	getReal ();
	protected abstract void 	setReal ( byte value );

}
