package com.aladdindb.finder.types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class DateFinder < 

	UDM 			extends Store 	< UDM >, 
	FINDER_MODEL	extends DateFinder	< UDM, FINDER_MODEL >

> extends DefaultFinder < UDM, FINDER_MODEL, LocalDate > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DateFinder( String operator, String pattern ) {
		super( operator, pattern );
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( LocalDate value) {
		var rv = new Var<Boolean>(false);
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				var pattern = LocalDate.parse( patternStr, DateTimeFormatter.ISO_LOCAL_DATE );
				rv.set( switch( operator.trim().toLowerCase() ) {
				
					case "=="	-> 	value.isEqual 	( pattern ); 
					case "!="	-> !value.isEqual 	( pattern ); 
					case ">"	-> 	value.isAfter 	( pattern );
					case ">="	-> 	value.isAfter 	( pattern) || value.isEqual( pattern );
					case "<"	-> 	value.isBefore	( pattern );
					case "<="	-> 	value.isBefore 	( pattern) || value.isEqual( pattern );
					
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
