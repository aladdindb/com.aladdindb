package com.xelara.aladdin.genie;

import java.util.function.Consumer;

import com.xelara.structure.sn.SnPoint;

public class BeforeWish {
	
	private final SnPoint wishSn;
	
	public BeforeWish( SnPoint wishSn ) {
		this.wishSn = wishSn;
	}

	public void getUnitGroupID( Consumer<String> consumer ) {
		var rv = this.getUnitGroupID();
		if( rv != null && !rv.trim().isEmpty() ) consumer.accept( rv );
	}
	
	public String getUnitGroupID() {
		return wishSn.attributes.getValue("unitGroupID");
	}
}
