package com.aladdindb.structure;

import com.aladdindb.util.Parent;

public abstract class DefaultDataModel < T extends DataModel< T > > implements DataModel < T >, Parent {

	private final Parent parent;
	
	public DefaultDataModel( Parent parent ) {
		this.parent = parent;
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
    	return this.getClass().getSimpleName();
	}
	
	
}
