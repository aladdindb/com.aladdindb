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
					case MATCHES 				-> fieldValue.matches				( pattern );
					case EQUAL 					-> fieldValue.equals				( pattern );
					
					case GREATER 				-> fieldValue.compareToIgnoreCase	( pattern ) > 	0;
					case GREATER_OR_EQUAL 		-> fieldValue.compareToIgnoreCase	( pattern ) >= 	0;
					
					case LESS 					-> fieldValue.compareToIgnoreCase	( pattern ) <	0;
					case LESS_OR_EQUAL 			-> fieldValue.compareToIgnoreCase	( pattern ) <=	0;
					//--------------------------------
					//				not
					//--------------------------------
					case NOT_MATCHES 			-> !fieldValue.matches				( pattern );
					case NOT_EQUAL 				-> !fieldValue.equals				( pattern );
					
					case NOT_GREATER 			-> !(fieldValue.compareToIgnoreCase	( pattern ) > 	0);
					case NOT_GREATER_OR_EQUAL 	-> !(fieldValue.compareToIgnoreCase	( pattern ) >= 	0);
					
					case NOT_LESS 				-> !(fieldValue.compareToIgnoreCase	( pattern ) <	0);
					case NOT_LESS_OR_EQUAL 		-> !(fieldValue.compareToIgnoreCase	( pattern ) <=	0);
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
