package com.xelara.aladdin.core.filter;

import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;

public abstract class ParserModelFilter <

	UDM 		extends DataModel 			< UDM >, 
	DATA_MODEL 	extends ParserModelFilter	< UDM, DATA_MODEL, VT >,
	VT
>

	extends 	DataParser	< DATA_MODEL > 
	implements 	DataModel 	< DATA_MODEL >,	
				Filter		< UDM > {
	
	public ParserModelFilter( String nodeKey ) {
		super( nodeKey );
	}
	
}
