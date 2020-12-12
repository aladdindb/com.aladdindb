package com.xelara.aladdin.model.list.input;

import java.util.StringTokenizer;
import java.util.function.Consumer;

import com.xelara.aladdin.unit.model.DbUnitListModel;
import com.xelara.core.Var;

public class InputListModel extends DbUnitListModel < InputModel, InputListModel  > {
	
	
	public InputListModel() {
	}
	
	/**
	 * 
	 * @param values Enthält mit komme getrennte Einträge
	 */
	public InputListModel( String values ) {
		var list = new StringTokenizer(values, ",");
		while( list.hasMoreElements() ) {
			this.add( list.nextToken() );
		}
	}
	
    public void add( String value ) {
    	if( value != null && !value.isEmpty() ) {
    		this.add( new InputModel( value ));
    	}
    }
    
    public void getFirst( Consumer< String > consumer ) {
    	var rv = getFirst();
    	if( rv != null ) consumer.accept( rv );
    }
    
    public String getFirst() {
		var rv = getFirstItem();
		return rv != null ? rv.entry.getValue() : null; 
    }
	
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	if( size() == 1) {
    		get(0).entry.getValue( sb :: append );
    	} else {
        	Var<Boolean> first = new Var<Boolean>( true );
        	forEach( email -> {
        		email.entry.getValue( entry -> {
        			sb.append( first.getValue() ? cover( entry ) : "," + cover( entry ) );
        		});
        		first.setValue( false );
        	});
    	}
    	return sb.toString();
    }
    
    private String cover( String str ) {
    	return ""+str+"";
    }
    
	
	public boolean existEntry( String entry ) {
        Var < Boolean > rv = new Var<>( false );
        this.forEach( inut -> {
	        if( inut.equalsEntry( entry ) ) rv.setValue( true );
		});
		return rv.getValue();
	}
}
