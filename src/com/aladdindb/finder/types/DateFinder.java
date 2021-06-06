package com.aladdindb.finder.types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.finder.DefaultFinderTransformer;
import com.aladdindb.finder.OP;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public class DateFinder < UDM extends DataModel < UDM > > extends DefaultFinder < UDM, DateFinder< UDM >, LocalDate > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	private String name;
	
	public DateFinder( Function < Unit < UDM >, Var< LocalDate > > unitFieldGetter ) {
		this( null, null, null, unitFieldGetter );
	}
	
	public DateFinder( String operator, String pattern, String name, Function < Unit < UDM >, Var< LocalDate > > unitFieldGetter ) {
		super( operator, pattern, unitFieldGetter );
		this.name = name;
	}

	@Override
	public  DefaultFinderTransformer< UDM, DateFinder< UDM >, LocalDate > newTransformer() {
		return new DefaultFinderTransformer<>( this.name ) {
			@Override 
			public DateFinder< UDM > newModel() {
				return new DateFinder<>( null, null, null, null );
			}
		};
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
