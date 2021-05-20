package com.aladdindb.store;

import com.aladdindb.structure.sn.SnPoint;

public class UnitIdBlock {

	private final int blockSize;
	
	private  StringBuilder 	block = new StringBuilder();
	private  String 		delim = "";
	
	private int index = 1;

	private int blockCounter = 0;
	
	private SnPoint blockNode = new SnPoint();

	
	public UnitIdBlock( int blockSize ) {
		this.blockSize 	= blockSize;
	}
	
	public void addUnitId( String... unitIds ) {
		for( String unitId : unitIds ) this.addUnitId(unitId);
	}
	
	public void addUnitId( String unitId ) {
		block.append( delim);
		block.append( unitId );
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
			
			System.out.printf( " Block was created : Number - %s%n", ++blockCounter );
		}
	}
	
	public UnitIdBlockNavi newUnitIdBlockNavi()  {
		this.saveBlock();
		return new UnitIdBlockNavi( blockNode );
	}
}
	