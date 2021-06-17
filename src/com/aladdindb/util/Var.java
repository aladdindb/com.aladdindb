package com.aladdindb.util;

import java.util.function.Consumer;

/**
 *
 * @author Macit Kandemir
 * @param <T>
 */

public class Var < T > implements Parent {

    private T value;
    
    private final Parent parent;
    
    public Var () {
    	this( null, null );
    }
    
    public Var ( Parent parent  ) {
    	this( parent, null );
    }
    
    public Var ( Parent parent, T value ) {
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
    
	@Override
	public String key() {
		if( this.parent != null ) {
	    	Class<?> clazz = this.parent.getClass();
	    	for( var field : clazz.getFields()) {
	    		try {
	    			Object o = field.get( parent );
	    	    	if( o == this ) {
	   	    			return parent.key()+"."+field.getName() ; 
	    	    	}
	    		} catch (IllegalArgumentException | IllegalAccessException e) {
	    			e.printStackTrace();
	    		}
	    	}
		}
    	return null;
	}
	
	public Parent getParent() {
		return parent;
	}
    
}
