package com.aladdindb.util;

public class TableOut {

	
	private final 	Column	[] columns;
	private final 	Object  [] labels;
	private final 	Object  [] lines;
	
	private final int firstColmnSize = 7;
	
	private final IntIndex rowIndex = new IntIndex( 1 );
	
	//---------------------------------------------------
	//
	//---------------------------------------------------
	
	public TableOut( Column...columns ) {
		
		
		this.columns = new Column[columns.length+1];

		this.labels = new String[ this.columns.length ];
		this.lines 	= new String[ this.columns.length ];

		this.columns[0] = new Column( firstColmnSize, "");
		
		for( int i = 0; i < columns.length; i++) {
			this.columns[i+1] = columns[i];
		}
		
		for( int i = 0; i < this.columns.length; i++ ) {
			this.labels	[i] = this.centerString( this.columns[i]);
			this.lines	[i] = Util.createString( this.columns[i].size, '-' );
		}
	}
	
	//---------------------------------------------------
	//
	//---------------------------------------------------

	public void printHead() {
		var sb = new StringBuilder();
		for( var row : columns) {
			sb.append( "%"+row.size+"s|");
		}
		sb.append( "%n");
		System.out.printf( sb.toString(), this.labels 	);
	}

	public void printData( Var<?>... vars ) {
		var data = new Object[ vars.length ];
		
		for( int i = 0; i < vars.length; i++ ) {
			data[i] = vars[i].get();
		}
		
		this.printData( data );
	}
	
	public void printData( Object...data) {
		var sb = new StringBuilder();
		sb.append( "%"+(columns[0].size-1)+"s.|");
		for( int i = 1; i < columns.length; i++ ) {
			sb.append( " %-"+(columns[i].size-1)+"s|");
		}
		sb.append( "%n");
		
		var tempData = new Object[data.length+1];
		
		tempData[0] = rowIndex.inc();
		
		for( int i = 0; i < data.length; i++ ) {
			tempData[i+1] = data[i];
		}
		
		System.out.printf( sb.toString()	,tempData );
	}

	public void printLine() {
		var sb = new StringBuilder();
		for( var row : columns) {
			sb.append( "%"+(row.size-1)+"s|");
		}
		sb.append( "--%n");
		System.out.printf( sb.toString(), lines );
	}

	//---------------------------------------------------
	//
	//---------------------------------------------------
	
	private String centerString ( Column column ) {
		var s	 	= column.label;
		var width 	= column.size; 
	    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
	
	public static Column newColumn( String label, int size ) {
		return new Column(size, label);
	}
	
	//---------------------------------------------------
	//
	//---------------------------------------------------
	
	public static class Column {
		
		public int 		size;
		public String 	label;
		
		public Column(  int size, String label ) {
			this.size 	= size;
			this.label 	= label;
		}
		
	}
}
