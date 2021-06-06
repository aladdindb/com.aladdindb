package com.aladdindb.util;

import java.util.function.Consumer;

import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 * @param <T>
 */

public class Var < T > {

    private T value;
    
    private final Object parent;
    
    public Var () {
    	this( null, null );
    }
    
    public Var ( T value ) {
    	this( value, null );
    }

    public Var ( T value, Object parent ) {
        this.value = value;
        this.parent = parent;
    }

    public void get ( Var < Consumer < T > > var )  {
    	var.get( this :: get );
    }

    public void get ( Consumer < T > consumer )  {
        T rv = get();
        if( rv != null ) consumer.accept( rv );
    }
    
    public T get() {
        return value;
    }
    
    public void set( Var<T> var ) {
    	var.get( this :: set );
    }
    
    public void set ( T value ) {
        this.value = value;
    }
    
    public boolean exist() {
    	return get() != null;
    }
    
    public String getType() {
    	return this.parent.getClass().getName();
    }
    
}
