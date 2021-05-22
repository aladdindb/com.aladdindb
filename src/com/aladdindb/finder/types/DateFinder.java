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
					case equal 						-> value.isEqual 	( date );
					
					case greater 					-> value.isAfter 	( date );
					case greaterOrEqual 			-> value.isAfter 	( date) || value.isEqual( date );
					
					case less 						-> value.isBefore	( date );
					case lessOrEqual 				-> value.isBefore 	( date) || value.isEqual( date );
					//--------------------------------
					//				not
					//--------------------------------
					case notEqual 					-> !value.isEqual 	( date );
					
					case notGreater 				-> !value.isAfter 	( date );
					case notGreaterOrEqual 		-> !(value.isAfter 	( date) || value.isEqual( date ));
					
					case notLess 					-> !value.isBefore	( date );
					case notLessOrEqual 			-> !(value.isBefore ( date) || value.isEqual( date ));
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
