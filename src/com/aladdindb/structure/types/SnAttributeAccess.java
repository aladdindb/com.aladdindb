package com.aladdindb.structure.types;

import com.aladdindb.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 */
public class SnAttributeAccess {
    
    public final SnIntParser     		asInt;
    public final SnFloatParser    		asFloat;
    public final SnDoubleParser    		asDouble;
    public final SnStringParser  		asStr;
    public final SnBooleanParser   		asBool;
    public final SnCDataParser  		asCData;
    public final SnDateParser   		asDate;
    public final SnEnumParser   		asEnum;
    
    public final SnZonedDateTimeParser  asXlrZonedDateTime;

    public SnAttributeAccess( SnPoint snPoint ) {
    	this.asInt 		= new SnIntParser		( snPoint );
    	this.asFloat 	= new SnFloatParser		( snPoint );
    	this.asDouble 	= new SnDoubleParser	( snPoint );
    	this.asStr 		= new SnStringParser	( snPoint );
    	this.asBool 	= new SnBooleanParser	( snPoint );
    	this.asCData 	= new SnCDataParser		( snPoint );
    	this.asDate 	= new SnDateParser		( snPoint );
    	this.asEnum 	= new SnEnumParser		( snPoint );
    	
    	this.asXlrZonedDateTime = new SnZonedDateTimeParser	( snPoint );
	}
	
    
}
