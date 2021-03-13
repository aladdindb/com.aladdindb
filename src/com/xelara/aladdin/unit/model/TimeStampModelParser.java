package com.xelara.aladdin.unit.model;

import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

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
	public TimeStampModel toModel( SnPoint node, TimeStampModel model ) {
    	var parse = new SnAttributeAccess( node );
    	
    	parse.asStr.get( ATR.create	, model.create 	);
    	parse.asStr.get( ATR.update	, model.update	);
    	
		return model;
	}

	@Override
	public SnPoint toNode( TimeStampModel model, SnPoint node ) {
    	var parse = new SnAttributeAccess( node );
    	
    	parse.asStr.set( ATR.create	, model.create );
    	parse.asStr.set( ATR.update	, model.update );
    	
		node.valueType.set( SnValueType.SINGLE_LINE);
		
		return node;
	}

}
