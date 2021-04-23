package com.xelara.aladdin.core.filter;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

public abstract class FilterString < UDM extends DataModel < UDM > > 
		extends FilterAbstract < UDM, FilterString< UDM >, String > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public FilterString( String tagKey, String operator, String pattern ) {
		super( tagKey, operator, pattern );
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( String value) {
		var rv = new Var<Boolean>(false);
		this.operator.get( operator -> {
			this.pattern.get( pattern -> {
				rv.set( switch( operator.trim().toLowerCase() ) {
				
					case "matches"	-> value.matches				( pattern ); 
					case ">"		-> value.compareToIgnoreCase	( pattern ) > 	0 ;
					case ">="		-> value.compareToIgnoreCase	( pattern ) >= 	0 ;
					case "<"		-> value.compareToIgnoreCase	( pattern ) < 	0 ;
					case "<="		-> value.compareToIgnoreCase	( pattern ) <= 	0 ;
					
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
