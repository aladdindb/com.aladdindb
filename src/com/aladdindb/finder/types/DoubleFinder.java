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
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 */
public  class DoubleFinder < UDM extends DataModel < UDM > > extends DefaultFinder < UDM, DoubleFinder< UDM >, Double > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	private String name;
	
	
	public DoubleFinder( String operator, String pattern, String name, Function < Unit < UDM >, Var< Double > > unitFieldGetter ) {
		super( operator, pattern, unitFieldGetter );
		this.name = name;
	}

	@Override
	public  DefaultFinderTransformer< UDM, DoubleFinder< UDM >, Double > newTransformer() {
		return new DefaultFinderTransformer<>( this.name ) {
			@Override 
			public DoubleFinder< UDM > newModel() {
				return new DoubleFinder<>( null, null, null, null );
			}
		};
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Double value) {
		
		var rv = new Var<Boolean>(false);
		
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				
				var operand = Double.parseDouble( patternStr );
				
				rv.set(	switch( OP.valueOf ( operator ) ) {
					//--------------------------------
					case equal 						-> value == 	operand ;
					
					case greater 					-> value > 		operand ;
					case greaterOrEqual 			-> value >= 	operand ;
					
					case less 						-> value < 		operand ;
					case lessOrEqual 				-> value <= 	operand ;
					//--------------------------------
					//				not
					//--------------------------------
					case notEqual 					->  value != 	operand ;
					
					case notGreater 				-> !(value >	operand );
					case notGreaterOrEqual 		-> !(value >= 	operand );
					
					case notLess 					-> !(value < 	operand );
					case notLessOrEqual 			-> !(value <= 	operand );
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
