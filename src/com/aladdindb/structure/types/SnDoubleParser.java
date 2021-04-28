package com.aladdindb.structure.types;

import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SnDoubleParser extends SnAttributeParser < Double > {
    

	public SnDoubleParser( SnPoint node ) {
    	super(node);
	}
	
    @Override
    public Double get( String key ) {
        var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? Double.parseDouble( value ) : null  ;
    }
    
    @Override
    public void set( String key, Double value ) {
        node.attributes.set( key, value.toString() );
    }
    
    
}
