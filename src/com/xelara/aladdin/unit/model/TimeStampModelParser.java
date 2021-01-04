package com.xelara.aladdin.unit.model;

import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

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
	public TimeStampModel parse(SNode src, TimeStampModel target) {
		Parser.STR.parse( ATR.create, src, target.create 	);
		Parser.STR.parse( ATR.update, src, target.update	);
		return target;
	}

	@Override
	public SNode parse( TimeStampModel src, SNode target ) {
		Parser.STR.parse( ATR.create, src.create, target );
		Parser.STR.parse( ATR.update, src.update, target );
		target.setValueType( SN.VALUE_TYPE_SL_VOID);
		return target;
	}

}
