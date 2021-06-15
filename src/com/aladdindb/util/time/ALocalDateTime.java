package com.aladdindb.util.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Macit Kandemir
 */
public class ALocalDateTime {
	

	public static String toISO() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( LocalDateTime.now());
	}
	
	public static String toISO( LocalDateTime dateTime ) {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( dateTime );
	}
	
	public static LocalDateTime fromISO( String dateTime ) {
		return LocalDateTime.parse( dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME  );
	}

}
