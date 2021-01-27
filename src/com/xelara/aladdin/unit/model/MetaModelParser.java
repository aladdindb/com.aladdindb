package com.xelara.aladdin.unit.model;

import com.xelara.aladdin.model.text.LabelModelParser;
import com.xelara.structure.node.Snode;

public class MetaModelParser extends DataModelParser<MetaModel> {

	private final LabelModelParser 		label 		= new LabelModelParser();
	private final TimeStampModelParser 	timeStamp 	= new TimeStampModelParser();
	
	public MetaModelParser() {
		super("meta");
	}	
	
	@Override
	public MetaModel newModel() {
		return new MetaModel();
	}

	@Override
	public MetaModel fromNode( Snode src, MetaModel target ) {
		label		.fromParentNode( src, target.label		);
		timeStamp	.fromParentNode( src, target.timeStamp );
		return target;
	}

	@Override
	public Snode toNode(MetaModel src, Snode target) {
		label		.toParentNode( src.label		,target );
		timeStamp	.toParentNode( src.timeStamp	,target );
		return target;
	}

}
