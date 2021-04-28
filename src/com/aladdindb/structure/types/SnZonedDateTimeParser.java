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
//        new XlrZonedDateTime().parseStr( value );
//        return value != null && !value.trim().isEmpty() ? new XlrZonedDateTime().parseStr( value ) : null  ;
//        new XlrZonedDateTime().parseIsoDateTimeStr( value );
        return value != null && !value.trim().isEmpty() ? AZonedDateTime.fromIsoStr( value ) : null  ;
    }
    
    @Override
    public void set( String key, ZonedDateTime value ) {
//        node.attributes.set( key, new XlrZonedDateTime().toStr( value ) );
        node.attributes.set( key, AZonedDateTime.toIsoStr( value ) );
    }
    
    
}
