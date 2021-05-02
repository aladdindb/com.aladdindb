package com.aladdindb.finder.types;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 */
public abstract class DoubleFinder < 

	UDM 	extends DataModel 		< UDM >, 
	MODEL	extends DoubleFinder	< UDM, MODEL >

> extends DefaultFinder < UDM, MODEL, Double > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DoubleFinder( String operator, String pattern ) {
		super( operator, pattern );
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
