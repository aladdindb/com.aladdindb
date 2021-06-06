package com.aladdindb.finder.types;

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
public class StringFinder < UDM extends DataModel < UDM > > extends DefaultFinder< UDM, StringFinder < UDM >, String  > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	private String name;
	
	public StringFinder( Function < Unit < UDM >, Var< String > > unitFieldGetter ) {
		this( null, null, null, unitFieldGetter );
	}
	
	public StringFinder( String operator, String pattern, String name, Function < Unit < UDM >, Var< String > > unitFieldGetter ) {
		super( operator, pattern, unitFieldGetter );
		this.name = name;
	}

	@Override
	public  DefaultFinderTransformer< UDM, StringFinder< UDM >, String > newTransformer() {
		return new DefaultFinderTransformer<>( name ) {
			 
			@Override 
			public StringFinder< UDM > newModel() {
				return new StringFinder<>( null, null, null, null );
			}
		};
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
