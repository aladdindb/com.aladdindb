package com.aladdindb.structure.sn;

import java.util.function.Consumer;

import com.aladdindb.util.Var;

/**
*
* @author Macit Kandemir
*/
public abstract class SnStringFeature extends SnFeature  {
	
	public SnStringFeature( SnPoint node  ) {
		super( node );
	}
	
    public final void set( Var<String> srcVar ) {
    	srcVar.get( this :: set );
    }
    
    public final void set( String str ) {
    	this.setReal( str );
    }

    public final void get( Var< String > targetVar ) {
    	get( targetVar::set );
    }

    public final void get( Consumer< String > consumer ) {
    	var rv = get();
    	if( rv != null )consumer.accept(rv);
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
