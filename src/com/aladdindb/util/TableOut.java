package com.aladdindb.util;

public class TableOut {

	
	private final 	Row		[] rows;
	private final 	Object  [] labels;
	private final 	Object  [] lines;
	
	//---------------------------------------------------
	//
	//---------------------------------------------------
	
	public TableOut( Row...rows ) {
		
		this.rows = rows;

		this.labels = new String[ rows.length ];
		this.lines 	= new String[ rows.length ];
		
		for( int i = 0; i < rows.length; i++ ) {
			this.labels	[i] = this.centerString( rows[i]);
			this.lines	[i] = Util.createString( rows[i].size, '-' );
		}
	}
	
	//---------------------------------------------------
	//
	//---------------------------------------------------

	public void printHead() {
		var sb = new StringBuilder();
		for( var row : rows) {
			sb.append( "%"+row.size+"s|");
		}
		sb.append( "%n");
		System.out.printf( sb.toString(), this.labels 	);
	}

	public void printData( Object...data) {
		var sb = new StringBuilder();
		sb.append( "%"+(rows[0].size-1)+"s.|");
		for( int i = 1; i < rows.length; i++ ) {
			sb.append( " %-"+(rows[i].size-1)+"s|");
		}
		sb.append( "%n");
		System.out.printf( sb.toString()	,data );
	}

	public void printLine() {
		var sb = new StringBuilder();
		for( var row : rows) {
			sb.append( "%"+(row.size-1)+"s|");
		}
		sb.append( "--%n");
		System.out.printf( sb.toString(), lines );
	}

	//---------------------------------------------------
	//
	//---------------------------------------------------
	
	private String centerString ( Row row ) {
		var s = row.label;
		var width = row.size; 
	    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
	
	public static Row column( String label, int size ) {
		return new Row(size, label);
	}
	
	//---------------------------------------------------
	//
	//---------------------------------------------------
	
	public static class Row {
		
		public int 		size;
		public String 	label;
		
		public Row(  int size, String label ) {
			this.size 	= size;
			this.label 	= label;
		}
		
	}
}
