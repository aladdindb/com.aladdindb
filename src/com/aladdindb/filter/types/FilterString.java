package com.aladdindb.filter.types;

import com.aladdindb.filter.FilterDefault;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 */
public abstract class FilterString <

	UDM 	extends DataModel 		< UDM >, 
	MODEL	extends FilterString	< UDM, MODEL >

> extends FilterDefault < UDM, MODEL, String  > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public FilterString( String operator, String pattern ) {
		super( operator, pattern );
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
					case "equals"	-> value.equals					( pattern );
					
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
