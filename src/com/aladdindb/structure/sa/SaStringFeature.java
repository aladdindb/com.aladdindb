package com.aladdindb.structure.sa;

import java.util.Objects;
import java.util.function.Consumer;

import com.aladdindb.structure.sa.props.SaPoint;

/**
*
* @author Macit Kandemir
*/
public abstract class SaStringFeature extends SaFeature  {
	
	public SaStringFeature( SaPoint saPoint  ) {
		super( saPoint );
	}
	
    public final void set( String str ) {
    	Objects.requireNonNull( str );
    	this.setReal( str );
    }

    public final void get( Consumer< String > consumer) {
    	var rv = get();
    	if( rv != null ) consumer.accept(rv);
    }
    
    public final String get() {
    	return this.getReal(); 
    }
	
    public final boolean exist() {
    	var str = this.getReal();
        return str != null && !str.trim().isEmpty();
    }
	
    @Override
    public boolean equals( Object obj ) {
    	var str = this.getReal();
    	return str != null && obj != null ? str.equals( obj ) : false;
    }
    
	protected abstract String 	getReal ();
	protected abstract void 	setReal ( String str );

}
