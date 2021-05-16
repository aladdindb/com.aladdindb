package com.aladdindb.units.models;

import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;
import com.aladdindb.structure.types.SnAttributeAccess;

public class TimeStampTransformer extends Transformer< TimeStamp > {

	private enum ATR { generatedOn, modifiedOn };
	
	public TimeStampTransformer() {
		super("timestamp");
	}
	
	@Override
	public TimeStamp newStore() {
		return new TimeStamp();
	}

	@Override
	public TimeStamp toStore( SnPoint src, TimeStamp target ) {
    	var srcAtr = new SnAttributeAccess( src );
    	
    	srcAtr.asXlrZonedDateTime.get( ATR.generatedOn	, target.generatedOn 	);
    	srcAtr.asXlrZonedDateTime.get( ATR.modifiedOn	, target.modifiedOn	);
    	
		return target;
	}

	@Override
	public SnPoint toNode( TimeStamp src, SnPoint target ) {
    	var targetAtr = new SnAttributeAccess( target );
    	
    	targetAtr.asXlrZonedDateTime.set( ATR.generatedOn	, src.generatedOn );
    	targetAtr.asXlrZonedDateTime.set( ATR.modifiedOn	, src.modifiedOn );

		return target;
	}

}
