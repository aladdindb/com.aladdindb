package com.xelara.aladdin.core.filter;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;


public abstract class FilterInt < UDM extends DataModel < UDM > > 
	extends FilterAbstract < UDM, Integer > {

	
	public FilterInt( String tagKey, String operator, String pattern ) {
		super( tagKey, operator, pattern );
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Integer value) {
		var rv = new Var< Boolean >(false);
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				var pattern = Integer.parseInt( patternStr );
				rv.set( switch( operator.trim().toLowerCase() ) {
				
					case "=="	-> value == 	pattern ; 
					case "!="	-> value != 	pattern ; 
					case ">"	-> value > 		pattern ;
					case ">="	-> value >= 	pattern ;
					case "<"	-> value < 		pattern ;
					case "<="	-> value <= 	pattern ;
					
					default -> false;
				});
			});
		});
		return rv.get();
	}
	
    //****************************************************************
    //
    //****************************************************************
	
}
