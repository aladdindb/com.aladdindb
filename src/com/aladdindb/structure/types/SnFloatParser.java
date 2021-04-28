package com.aladdindb.structure.types;

import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SnFloatParser extends SnAttributeParser < Float > {
    

	public SnFloatParser( SnPoint node ) {
    	super(node);
	}
	
    @Override
    public Float get( String key ) {
        var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? Float.parseFloat( value ) : null  ;
    }
    
    @Override
    public void set( String key, Float value ) {
        node.attributes.set( key, value.toString() );
    }
    
    
}
