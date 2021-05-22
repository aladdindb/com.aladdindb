package com.aladdindb.finder.types;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.finder.OP;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class StringFinder <

	UDM 	extends DataModel 		< UDM >, 
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
	public boolean provePattern( String fieldValue) {
		var rv = new Var<Boolean>(false);
		this.operator.get( operator -> {
			this.pattern.get( pattern -> {
				
				rv.set(	switch( OP.valueOf ( operator ) ) {
					//--------------------------------
					case matches 				-> fieldValue.matches				( pattern );
					case equal 					-> fieldValue.equals				( pattern );
					
					case greater 				-> fieldValue.compareToIgnoreCase	( pattern ) > 	0;
					case greaterOrEqual 		-> fieldValue.compareToIgnoreCase	( pattern ) >= 	0;
					
					case less 					-> fieldValue.compareToIgnoreCase	( pattern ) <	0;
					case lessOrEqual 			-> fieldValue.compareToIgnoreCase	( pattern ) <=	0;
					//--------------------------------
					//				not
					//--------------------------------
					case notMatches 			-> !fieldValue.matches				( pattern );
					case notEqual 				-> !fieldValue.equals				( pattern );
					
					case notGreater 			-> !(fieldValue.compareToIgnoreCase	( pattern ) > 	0);
					case notGreaterOrEqual 	-> !(fieldValue.compareToIgnoreCase	( pattern ) >= 	0);
					
					case notLess 				-> !(fieldValue.compareToIgnoreCase	( pattern ) <	0);
					case notLessOrEqual 		-> !(fieldValue.compareToIgnoreCase	( pattern ) <=	0);
					//--------------------------------
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
