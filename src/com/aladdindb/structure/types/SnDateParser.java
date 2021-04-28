package com.aladdindb.structure.types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SnDateParser extends SnAttributeParser < LocalDate > {
    

	public SnDateParser( SnPoint node ) {
    	super(node);
	}
	
    @Override
    public LocalDate get( String key ) {
        var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? LocalDate.parse( value, DateTimeFormatter.ISO_LOCAL_DATE ) : null  ;
    }
    
    @Override
    public void set( String key, LocalDate value ) {
        node.attributes.set( key, value.format( DateTimeFormatter.ISO_LOCAL_DATE ) );
    }
    
    
}
