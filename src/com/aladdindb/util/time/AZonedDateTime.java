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
	
	public static String toRfc1123() {
		return DateTimeFormatter.RFC_1123_DATE_TIME.format( ZonedDateTime.now());
	}
	
	public static ZonedDateTime fromRfc1123( String zonedDateTime ) {
		return ZonedDateTime.parse( zonedDateTime, DateTimeFormatter.RFC_1123_DATE_TIME  );
	}

	public static String toRfc1123( ZonedDateTime zonedDateTime ) {
		return DateTimeFormatter.RFC_1123_DATE_TIME.format( zonedDateTime );
	}
	
	//*********************************************************************
	
	public static String toISO() {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format( ZonedDateTime.now());
	}
	
	public static String toISO( ZonedDateTime zonedDateTime ) {
		return DateTimeFormatter.ISO_ZONED_DATE_TIME.format( zonedDateTime );
	}
	
	public static ZonedDateTime fromISO( String zonedDateTime ) {
		return ZonedDateTime.parse( zonedDateTime, DateTimeFormatter.ISO_ZONED_DATE_TIME  );
	}

	//*********************************************************************
}
