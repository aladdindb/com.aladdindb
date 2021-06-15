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

	public ZonedDateTimeFinder( Class<UDM> udmClass,Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( null, null, udmClass, unitFieldGetter );
	}
	
	public ZonedDateTimeFinder( String operator, String pattern, Class<UDM> udmClass, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		super( operator, pattern, udmClass, unitFieldGetter );
	}

	@Override
	public  DefaultFinderTransformer< UDM, ZonedDateTimeFinder< UDM >, ZonedDateTime > newTransformer() {
		return new DefaultFinderTransformer<>() {
			@Override 
			public ZonedDateTimeFinder< UDM > newModel() {
				return new ZonedDateTimeFinder<>( null, null, null, null );
			}
		};
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( ZonedDateTime value) {
		
		var rv = new Var<Boolean>(false);
		
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
