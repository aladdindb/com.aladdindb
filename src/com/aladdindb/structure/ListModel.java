package com.aladdindb.structure;

import java.util.ArrayList;

import com.aladdindb.util.Parent;

public class ListModel <

		LIST_ITEM_TYPE extends DataModel < LIST_ITEM_TYPE > 

> extends ArrayList< LIST_ITEM_TYPE > implements DataModel< ListModel < LIST_ITEM_TYPE > >, Parent {

	private Parent parent;
	
	public ListModel( Parent parent ) {
		this.parent = parent;
	}
	
	@Override
	public void fill( ListModel<LIST_ITEM_TYPE> model) {
		model.forEach( this :: add );
	}
	
	@Override
	public String key() {
		if( this.parent != null ) {
	    	Class<?> clazz = this.parent.getClass();
	    	for( var field : clazz.getFields()) {
	    		try {
	    			Object o = field.get( parent );
	    	    	if( o == this ) {
	    	    		String parentKey = parent.key();
	   	    			return parentKey+"."+field.getName() ; 
	    	    	}
	    		} catch (IllegalArgumentException | IllegalAccessException e) {
	    			e.printStackTrace();
	    		}
	    	}
		}
    	return null;
	}
	
}
