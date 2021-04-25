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
public abstract class FilterInt < 

	UDM 	extends DataModel 	< UDM >, 
	MODEL	extends FilterInt	< UDM, MODEL >

> extends FilterDefault < UDM, MODEL, Integer  > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public FilterInt( String operator, String pattern ) {
		super( operator, pattern );
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
