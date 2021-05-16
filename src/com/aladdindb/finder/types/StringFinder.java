package com.aladdindb.finder.types;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class StringFinder <

	UDM 	extends Store 		< UDM >, 
	MODEL	extends StringFinder	< UDM, MODEL >

> extends DefaultFinder < UDM, MODEL, String  > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public StringFinder( String operator, String pattern ) {
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
