package com.aladdindb.util;

public class FiledName {

	public final String key;
	
	public FiledName( String... keys ) {
		var sb = new StringBuilder();
		sb.append( keys[0] );
		for( int i = 1; i < keys.length; i++) {
			sb.append('.');
			sb.append( keys[i]);
		}
		this.key = sb.toString();
	}
	
	public String finder() {
		return "Finder:"+key;
	}
	
	public String sorter() {
		return "Sorter:"+key;
	}
	
}
