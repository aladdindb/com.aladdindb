package com.xelara.aladdin.unit.model;

import com.xelara.structure.node.Snode;
import com.xelara.structure.attributes.AParser;
import com.xelara.structure.node.SnValueType;

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
	public TimeStampModel fromNode( Snode node, TimeStampModel model ) {
    	var parse = new AParser( node );
    	
    	parse.strPrs.get( ATR.create	, model.create 	);
    	parse.strPrs.get( ATR.update	, model.update	);
    	
		return model;
	}

	@Override
	public Snode toNode( TimeStampModel model, Snode node ) {
    	var parse = new AParser( node );
    	
    	parse.strPrs.set( ATR.create	, model.create );
    	parse.strPrs.set( ATR.update	, model.update );
    	
		node.setValueType( SnValueType.SL_VOID);
		
		return node;
	}

}
