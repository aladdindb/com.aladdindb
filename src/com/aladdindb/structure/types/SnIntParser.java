package com.aladdindb.structure.types;

import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SnIntParser extends SnAttributeParser < Integer > {
    

	public SnIntParser( SnPoint node ) {
    	super(node);
	}
	
    @Override
    public Integer get( String key ) {
        var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? Integer.parseInt( value ) : null  ;
    }
    
    @Override
    public void set( String key, Integer value ) {
        node.attributes.set( key, value.toString() );
    }
    
    
}
