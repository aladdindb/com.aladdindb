package com.xelara.aladdin.core.filter.types;

import com.xelara.aladdin.core.filter.FilterDefault;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 */
public abstract class FilterFloat <

	UDM 	extends DataModel 	< UDM >, 
	MODEL	extends FilterFloat	< UDM, MODEL >

> extends FilterDefault < UDM, MODEL, Float > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public FilterFloat( String operator, String pattern ) {
		super( operator, pattern );
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Float value) {
		var rv = new Var<Boolean>(false);
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				var pattern = Float.parseFloat( patternStr );
				
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
