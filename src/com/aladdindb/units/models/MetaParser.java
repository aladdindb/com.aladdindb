package com.aladdindb.units.models;

import com.aladdindb.defaultmodels.text.LabelParser;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;

public class MetaParser extends DataParser<Meta> {

	private final LabelParser 		label 	= new LabelParser();
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
