package com.xelara.aladdin.core.filter;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;


public abstract class FilterDouble < UDM extends DataModel < UDM > > 
	extends FilterAbstract < UDM, Double > {

	
	public FilterDouble( String tagKey, String operator, String pattern ) {
		super( tagKey, operator, pattern );
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Double value) {
		var rv = new Var<Boolean>(false);
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				var pattern = Double.parseDouble( patternStr );
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
