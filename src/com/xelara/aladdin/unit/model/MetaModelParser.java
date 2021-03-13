package com.xelara.aladdin.unit.model;

import com.xelara.aladdin.model.text.LabelModelParser;
import com.xelara.structure.sn.SnPoint;

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
	public MetaModel toModel( SnPoint src, MetaModel target ) {
		label		.toModelFromParent( src, target.label		);
		timeStamp	.toModelFromParent( src, target.timeStamp );
		return target;
	}

	@Override
	public SnPoint toNode(MetaModel src, SnPoint target) {
		label		.toParentNode( src.label		,target );
		timeStamp	.toParentNode( src.timeStamp	,target );
		return target;
	}

}
