package com.aladdindb.finder.types;

import java.time.ZonedDateTime;
import java.util.function.Function;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.finder.DefaultFinderTransformer;
import com.aladdindb.finder.OP;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;
import com.aladdindb.util.time.AZonedDateTime;


/**
 * 
 * @author Macit Kandemir
 *
 */
public class ZonedDateTimeFinder < UDM extends DataModel < UDM > > extends DefaultFinder < UDM, ZonedDateTimeFinder< UDM >, ZonedDateTime > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public ZonedDateTimeFinder( Class<UDM> udmClass, String operator, String pattern, Var< ? > varObject ) {
		super( udmClass, operator, pattern, null );
		this.fieldId.set( varObject.key() );
	}
	
	public ZonedDateTimeFinder( Class<UDM> udmClass,Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( udmClass, null, null, unitFieldGetter );
	}
	
	public ZonedDateTimeFinder( Class<UDM> udmClass, String operator, String pattern, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		super( udmClass, operator, pattern, unitFieldGetter );
		this.fieldId.set( getField() );
	}

	@Override
	public  DefaultFinderTransformer< UDM, ZonedDateTimeFinder< UDM >, ZonedDateTime > newTransformer() {
		return new DefaultFinderTransformer<>() {
			@Override 
			public ZonedDateTimeFinder< UDM > newModel() {
				return new ZonedDateTimeFinder<>( null, null, null, (Var)null );
			}
		};
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( ZonedDateTime value) {
		
		var rv = new Var<Boolean>( null, false );
		
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				
				var dateTime = AZonedDateTime.fromISO( patternStr );
				
				rv.set(	switch( OP.valueOf ( operator ) ) {
					//--------------------------------
					case equal 						-> value.isEqual 	( dateTime );
					
					case greater 					-> value.isAfter 	( dateTime );
					case greaterOrEqual 			-> value.isAfter 	( dateTime) || value.isEqual( dateTime );
					
					case less 						-> value.isBefore	( dateTime );
					case lessOrEqual 				-> value.isBefore 	( dateTime) || value.isEqual( dateTime );
					//--------------------------------
					//				not
					//--------------------------------
					case notEqual 					-> !value.isEqual 	( dateTime );
					
					case notGreater 				-> !value.isAfter 	( dateTime );
					case notGreaterOrEqual 			-> !(value.isAfter 	( dateTime) || value.isEqual( dateTime ));
					
					case notLess 					-> !value.isBefore	( dateTime );
					case notLessOrEqual 			-> !(value.isBefore ( dateTime) || value.isEqual( dateTime ));
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
