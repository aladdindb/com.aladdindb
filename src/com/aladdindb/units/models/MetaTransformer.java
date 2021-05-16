package com.aladdindb.units.models;

import com.aladdindb.models.text.LabelParser;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;

public class MetaTransformer extends Transformer<Meta> {

	private final LabelParser 			label 		= new LabelParser();
	private final TimeStampTransformer 	timeStamp 	= new TimeStampTransformer();
	
	public MetaTransformer() {
		super("meta");
	}	
	
	@Override
	public Meta newStore() {
		return new Meta();
	}

	@Override
	public Meta toStore( SnPoint src, Meta target ) {
		label		.toStoreFromParent( src, target.label		);
		timeStamp	.toStoreFromParent( src, target.timeStamp 	);
		return target;
	}

	@Override
	public SnPoint toNode(Meta src, SnPoint target) {
		label		.toParentNode( src.label		,target );
		timeStamp	.toParentNode( src.timeStamp	,target );
		return target;
	}

}
