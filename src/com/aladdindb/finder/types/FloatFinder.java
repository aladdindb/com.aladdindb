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
public class FloatFinder < UDM 	extends DataModel 	< UDM > > extends DefaultFinder < UDM, FloatFinder< UDM >, Float > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	public FloatFinder( Class<UDM> udmClass, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		this( udmClass, null, null, unitFieldGetter );
	}

	public FloatFinder( Class<UDM> udmClass, String operator, String pattern, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		super( udmClass, operator, pattern, unitFieldGetter );
		this.fieldId.set( getField() );
	}

	@Override
	public  DefaultFinderTransformer< UDM, FloatFinder< UDM >, Float > newTransformer() {
		return new DefaultFinderTransformer<>() {
			@Override 
			public FloatFinder< UDM > newModel() {
				return new FloatFinder<>( null, null, null, null );
			}
		};
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Float value) {
		
		var rv = new Var<Boolean>( null,false );
		
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				
				var operand =  Float.parseFloat( patternStr );
				
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
