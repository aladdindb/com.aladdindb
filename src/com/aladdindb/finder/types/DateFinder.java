package com.aladdindb.finder.types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.finder.OP;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class DateFinder < 

	UDM 			extends DataModel 	< UDM >, 
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
				
				var date = LocalDate.parse( patternStr, DateTimeFormatter.ISO_LOCAL_DATE );
				
				rv.set(	switch( OP.valueOf ( operator ) ) {
					//--------------------------------
					case EQUAL 						-> value.isEqual 	( date );
					
					case GREATER 					-> value.isAfter 	( date );
					case GREATER_OR_EQUAL 			-> value.isAfter 	( date) || value.isEqual( date );
					
					case LESS 						-> value.isBefore	( date );
					case LESS_OR_EQUAL 				-> value.isBefore 	( date) || value.isEqual( date );
					//--------------------------------
					//				not
					//--------------------------------
					case NOT_EQUAL 					-> !value.isEqual 	( date );
					
					case NOT_GREATER 				-> !value.isAfter 	( date );
					case NOT_GREATER_OR_EQUAL 		-> !(value.isAfter 	( date) || value.isEqual( date ));
					
					case NOT_LESS 					-> !value.isBefore	( date );
					case NOT_LESS_OR_EQUAL 			-> !(value.isBefore ( date) || value.isEqual( date ));
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
