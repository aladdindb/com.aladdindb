package com.aladdindb.util.time;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Macit Kandemir
 */
public class AZonedDateTime {
	
	
//	private static final DateTimeFormatter defaultFormatter() { 
//		return  DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss X"); 
//	}
	
	//*********************************************************************
	
//	public String timeStamp() {
//		return ZonedDateTime.now().format( XLR_ZONED_DATE_TIME );
//	}
//
//	public ZonedDateTime parseStr( String timeStamp ) {
//		return ZonedDateTime.parse( timeStamp, XLR_ZONED_DATE_TIME );
//	}
//
//	public String toStr( ZonedDateTime timeStamp ) {
//		return timeStamp.format( XLR_ZONED_DATE_TIME );
//	}
	
	//*********************************************************************
	
	public static String toIsoStr() {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format( ZonedDateTime.now());
	}
	
	public static ZonedDateTime fromIsoStr( String isoDateTimeStr ) {
		return ZonedDateTime.parse( isoDateTimeStr, DateTimeFormatter.ISO_ZONED_DATE_TIME  );
	}

	public static String toIsoStr( ZonedDateTime dateTime ) {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format( dateTime );
	}
	
	//*********************************************************************
}
