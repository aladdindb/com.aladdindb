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
    
    public Var ( T value ) {
        this.value = value;
    }

    public Var () {
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
    
}
