package com.aladdindb.method.req;

import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;

public class ReqNode {
	
	private final SnPoint reqNode;
	
	public ReqNode( SnPoint reqNode ) {
		this.reqNode = reqNode;
	}

	public void getStoreId( Consumer<String> consumer ) {
		var rv = this.getStoreId();
		if( rv != null && !rv.trim().isEmpty() ) consumer.accept( rv );
	}
	
	public String getStoreId() { 
		return reqNode.attributes.getValue( ReqTransformer.ATR.storeId.name() );
	}
}
