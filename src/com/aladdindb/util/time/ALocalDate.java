package com.aladdindb.util.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Macit Kandemir
 */
public class ALocalDate {
	
	
	public static String toISO() {
		return DateTimeFormatter.ISO_LOCAL_DATE.format( LocalDate.now() );
	}
	
	public static String toISO( LocalDate date ) {
		return DateTimeFormatter.ISO_LOCAL_DATE.format( date );
	}
	
	public static LocalDate fromISO( String date ) {
		return LocalDate.parse( date, DateTimeFormatter.ISO_LOCAL_DATE  );
	}
	
	
}
