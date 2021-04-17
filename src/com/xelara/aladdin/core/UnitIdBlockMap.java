package com.xelara.aladdin.core;

import com.xelara.structure.sn.SnPoint;

public class UnitIdBlockMap {

	private final int blockSize;
	
	private  StringBuilder 	block = new StringBuilder();
	private  String 		delim = "";
	
	private int index = 1;

	private int blockCounter = 0;
	
	private SnPoint blockNode = new SnPoint();

	
	public UnitIdBlockMap( int blockSize ) {
		this.blockSize 	= blockSize;
	}
	
	public void addUnitID( String... unitIDs ) {
		for( String unitID:unitIDs ) this.addUnitID(unitID);
	}
	
	public void addUnitID( String unitID ) {
		block.append( delim);
		block.append( unitID );
		this.delim = ",";
		if( index++ == this.blockSize ) saveBlock();
	}
	
	private void saveBlock() {
		if( block.length() > 0 ) {
			blockNode.children.add( new SnPoint("",  block.toString() ));
//			block.delete( 0 , block.length() );
			block = new StringBuilder();
			
			this.index = 1;
			this.delim = "";
			
			System.out.println( "Block was created : Number - "+ ++blockCounter );
		}
	}
	
	public UnitIdBlockNavi createBlockNavi()  {
		this.saveBlock();
		return new UnitIdBlockNavi( blockNode );
	}
}
	