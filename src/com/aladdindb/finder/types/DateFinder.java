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
import com.aladdindb.util.time.ALocalDate;


/**
 * 
 * @author Macit Kandemir
 *
 */
public class DateFinder < UDM extends DataModel < UDM > > extends DefaultFinder < UDM, DateFinder< UDM >, LocalDate > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DateFinder( Class<UDM> udmClass, String operator, String pattern, Var< ? > varObject ) {
		super( udmClass, operator, pattern, null );
		this.fieldId.set( varObject.key() );
	}
	
	public DateFinder( Class<UDM> udmClass, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( udmClass, null, null, unitFieldGetter );
	}
	
	public DateFinder( Class<UDM> udmClass, String operator, String pattern, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		super( udmClass, operator, pattern, unitFieldGetter );
		this.fieldId.set( getField() );
	}

	@Override
	public  DefaultFinderTransformer< UDM, DateFinder< UDM >, LocalDate > newTransformer() {
		return new DefaultFinderTransformer<>() {
			@Override 
			public DateFinder< UDM > newModel() {
				return new DateFinder<>( null, null, null,(Var) null );
			}
		};
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( LocalDate value) {
		
		var rv = new Var<Boolean>( null,false );
		
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				
				var date = ALocalDate.fromISO( patternStr );
				
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
					case notGreaterOrEqual 			-> !(value.isAfter 	( date) || value.isEqual( date ));
					
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
