package com.xelara.aladdin.core.units.models;

import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.core.models.text.LabelModelParser;
import com.xelara.structure.sn.SnPoint;

public class MetaParser extends DataParser<Meta> {

	private final LabelModelParser 		label 	= new LabelModelParser();
	private final TimeStampParser 	timeStamp 	= new TimeStampParser();
	
	public MetaParser() {
		super("meta");
	}	
	
	@Override
	public Meta newModel() {
		return new Meta();
	}

	@Override
	public Meta toModel( SnPoint src, Meta target ) {
		label		.toModelFromParent( src, target.label		);
		timeStamp	.toModelFromParent( src, target.timeStamp 	);
		return target;
	}

	@Override
	public SnPoint toNode(Meta src, SnPoint target) {
		label		.toParentNode( src.label		,target );
		timeStamp	.toParentNode( src.timeStamp	,target );
		return target;
	}

}
