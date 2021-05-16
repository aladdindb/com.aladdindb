package com.aladdindb.finder.types;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 */
public abstract class IntFinder < 

	UDM 	extends Store 	< UDM >, 
	MODEL	extends IntFinder	< UDM, MODEL >

> extends DefaultFinder < UDM, MODEL, Integer  > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public IntFinder( String operator, String pattern ) {
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
