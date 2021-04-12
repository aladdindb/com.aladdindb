package com.xelara.aladdin.server;

import java.util.function.Consumer;

import com.xelara.structure.sn.SnPoint;

public class ReqNode {
	
	private final SnPoint reqNode;
	
	public ReqNode( SnPoint reqNode ) {
		this.reqNode = reqNode;
	}

	public void getUnitGroupID( Consumer<String> consumer ) {
		var rv = this.getUnitGroupID();
		if( rv != null && !rv.trim().isEmpty() ) consumer.accept( rv );
	}
	
	public String getUnitGroupID() {
		return reqNode.attributes.getValue("unitGroupID");
	}
}
