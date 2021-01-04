package com.xelara.aladdin.unit.model;

import com.xelara.aladdin.model.text.LabelModelParser;
import com.xelara.structure.snode.SNode;

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
	public MetaModel parse( SNode src, MetaModel target ) {
		label		.parseFromParent( src, target.label		);
		timeStamp	.parseFromParent( src, target.timeStamp );
		return target;
	}

	@Override
	public SNode parse(MetaModel src, SNode target) {
		label		.parseToParent( src.label		,target );
		timeStamp	.parseToParent( src.timeStamp	,target );
		return target;
	}

}
