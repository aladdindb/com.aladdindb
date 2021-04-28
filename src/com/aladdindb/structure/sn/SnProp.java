package com.aladdindb.structure.sn;

import java.util.Objects;
import java.util.function.Consumer;

import com.aladdindb.structure.sn.props.Sn;

/**
*
* @author Macit Kandemir
*/
public abstract class SnProp extends SnFeature  {
	
	public SnProp( SnPoint snPoint  ) {
		super( snPoint );
	}
	
    public final void set( SnPoint snPoint ) {
    	Objects.requireNonNull( snPoint );
    	this.setReal( snPoint.getSN() );
    }

    public final void get( Consumer< SnPoint > consumer ) {
    	var rv =  this.get();
    	if( rv != null ) consumer.accept( rv );
    }
    
    public final SnPoint get() {
    	var sn = this.getReal(); 
        return sn == null ? null : new SnPoint( sn );
    }
	
    public final boolean exist() {
        return this.getReal() != null;
    }
	
	protected abstract Sn 		getReal ();
	protected abstract void 	setReal ( Sn sn );

}
