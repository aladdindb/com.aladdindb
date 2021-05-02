package com.aladdindb.units.models;

import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;
import com.aladdindb.structure.types.SnAttributeAccess;

public class TimeStampParser extends DataTransformer< TimeStamp > {

	private enum ATR { create, update };
	
	public TimeStampParser() {
		super("timestamp");
	}
	
	@Override
	public TimeStamp newModel() {
		return new TimeStamp();
	}

	@Override
	public TimeStamp toModel( SnPoint src, TimeStamp target ) {
    	var srcAtr = new SnAttributeAccess( src );
    	
    	srcAtr.asXlrZonedDateTime.get( ATR.create	, target.create 	);
    	srcAtr.asXlrZonedDateTime.get( ATR.update	, target.update	);
    	
		return target;
	}

	@Override
	public SnPoint toNode( TimeStamp src, SnPoint target ) {
    	var targetAtr = new SnAttributeAccess( target );
    	
    	targetAtr.asXlrZonedDateTime.set( ATR.create	, src.create );
    	targetAtr.asXlrZonedDateTime.set( ATR.update	, src.update );

		return target;
	}

}
