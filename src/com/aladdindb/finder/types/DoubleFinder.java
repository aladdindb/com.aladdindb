package com.aladdindb.finder.types;

import com.aladdindb.finder.DefaultFinder;
import com.aladdindb.finder.OP;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 */
public abstract class DoubleFinder < 

	UDM 	extends DataModel 		< UDM >, 
	MODEL	extends DoubleFinder	< UDM, MODEL >

> extends DefaultFinder < UDM, MODEL, Double > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public DoubleFinder( String operator, String pattern ) {
		super( operator, pattern );
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
					case EQUAL 						-> value == 	operand ;
					
					case GREATER 					-> value > 		operand ;
					case GREATER_OR_EQUAL 			-> value >= 	operand ;
					
					case LESS 						-> value < 		operand ;
					case LESS_OR_EQUAL 				-> value <= 	operand ;
					//--------------------------------
					//				not
					//--------------------------------
					case NOT_EQUAL 					->  value != 	operand ;
					
					case NOT_GREATER 				-> !(value >	operand );
					case NOT_GREATER_OR_EQUAL 		-> !(value >= 	operand );
					
					case NOT_LESS 					-> !(value < 	operand );
					case NOT_LESS_OR_EQUAL 			-> !(value <= 	operand );
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
