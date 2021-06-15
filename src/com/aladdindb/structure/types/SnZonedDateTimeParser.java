package com.aladdindb.structure.types;

import java.time.ZonedDateTime;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.time.AZonedDateTime;

/**
*
* @author Macit Kandemir
*/
public class SnZonedDateTimeParser extends SnAttributeParser < ZonedDateTime > {
    

	public SnZonedDateTimeParser( SnPoint node ) {
    	super(node);
	}
	
    @Override
    public ZonedDateTime get( String key ) {
        var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? AZonedDateTime.fromISO( value ) : null  ;
    }
    
    @Override
    public void set( String key, ZonedDateTime value ) {
        node.attributes.set( key, AZonedDateTime.toISO( value ) );
    }
    
    
}
