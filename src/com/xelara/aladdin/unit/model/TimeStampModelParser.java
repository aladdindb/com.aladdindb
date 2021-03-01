package com.xelara.aladdin.unit.model;

import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnParser;

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
    	var parse = new SnParser( node );
    	
    	parse._str.get( ATR.create	, model.create 	);
    	parse._str.get( ATR.update	, model.update	);
    	
		return model;
	}

	@Override
	public SnPoint toNode( TimeStampModel model, SnPoint node ) {
    	var parse = new SnParser( node );
    	
    	parse._str.set( ATR.create	, model.create );
    	parse._str.set( ATR.update	, model.update );
    	
		node.valueType.set( SnValueType.SINGLE_LINE);
		
		return node;
	}

}
