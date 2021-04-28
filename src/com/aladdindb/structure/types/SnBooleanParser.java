package com.aladdindb.structure.types;

import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SnBooleanParser extends SnAttributeParser < Boolean > {

	
	public SnBooleanParser( SnPoint node ) {
    	super(node);
	}
	
    @Override
    public Boolean get( String key ) {
    	var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? Boolean.parseBoolean( value ) : null  ;
    }
    
    @Override
    public void set( String key, Boolean value ) {
        node.attributes.set( key, value.toString() );
    }
   
}
