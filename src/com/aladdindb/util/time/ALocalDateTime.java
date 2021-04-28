package com.aladdindb.util.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Macit Kandemir
 */
public class ALocalDateTime {
	

//	private final DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");    	
	
	
	//*********************************************************************
	
//	public String timeStampAsFileName() {
//		return LocalDateTime.now().format( fileNameFormatter );
//	}
//
//	public LocalDateTime parseTimeStampAsFileName( String timeStampAsFileName ) {
//		return LocalDateTime.parse( timeStampAsFileName, fileNameFormatter );
//	}
    
	//*********************************************************************

	public static String toIsoStr() {
		return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( LocalDateTime.now());
	}
	
	public static LocalDateTime fromIsoStr( String isoDateTime ) {
		return LocalDateTime.parse( isoDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME  );
	}

	//*********************************************************************
}
