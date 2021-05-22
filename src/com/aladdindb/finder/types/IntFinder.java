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
public abstract class IntFinder < 

	UDM 	extends DataModel 	< UDM >, 
	MODEL	extends IntFinder	< UDM, MODEL >

> extends DefaultFinder < UDM, MODEL, Integer  > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public IntFinder( String operator, String pattern ) {
		super( operator, pattern );
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Integer value) {
		
		var rv = new Var< Boolean >(false);
		
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				
				var operand = Integer.parseInt( patternStr );
				
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
