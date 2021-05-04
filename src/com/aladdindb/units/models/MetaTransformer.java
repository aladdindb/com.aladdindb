package com.aladdindb.units.models;

import com.aladdindb.defaultmodels.text.LabelParser;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;

public class MetaTransformer extends DataTransformer<Meta> {

	private final LabelParser 		label 	= new LabelParser();
	private final TimeStampTransformer 	timeStamp 	= new TimeStampTransformer();
	
	public MetaTransformer() {
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
