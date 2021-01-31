package com.xelara.aladdin.unit.model;

import com.xelara.structure.sn.SnValueType;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.AParser;

public class TimeStampModelParser extends DataModelParser< TimeStampModel > {

	private enum ATR { create, update };
	
	public TimeStampModelParser() {
		super("timestamp");
	}
	
	@Override
	public TimeStampModel newModel() {
		return new TimeStampModel();
	}

	@Override
	public TimeStampModel fromNode( SnPoint node, TimeStampModel model ) {
    	var parse = new AParser( node );
    	
    	parse.strPrs.get( ATR.create	, model.create 	);
    	parse.strPrs.get( ATR.update	, model.update	);
    	
		return model;
	}

	@Override
	public SnPoint toNode( TimeStampModel model, SnPoint node ) {
    	var parse = new AParser( node );
    	
    	parse.strPrs.set( ATR.create	, model.create );
    	parse.strPrs.set( ATR.update	, model.update );
    	
		node.valueType.set( SnValueType.SINGLE_LINE);
		
		return node;
	}

}
