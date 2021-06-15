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
public class BooleanFinder < UDM extends DataModel < UDM > > extends DefaultFinder < UDM, BooleanFinder < UDM >, Boolean  > {

	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public BooleanFinder( String operator, String pattern, Class<UDM> udmClass, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		super( operator, pattern, udmClass, unitFieldGetter );
	}

	@Override
	public  DefaultFinderTransformer< UDM, BooleanFinder< UDM >, Boolean > newTransformer() {
		return new DefaultFinderTransformer<>() {
			@Override 
			public BooleanFinder< UDM > newModel() {
				return new BooleanFinder<>( null, null, null, null );
			}
		};
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Boolean value) {
		
		var rv = new Var< Boolean >(false);
		
		this.operator.get( operator -> {
			this.pattern.get( patternStr -> {
				
				var operand = Boolean.parseBoolean( patternStr );
				
				rv.set(	switch( OP.valueOf ( operator ) ) {
					//--------------------------------
					case equal -> value == 	operand ;
					
					//--------------------------------
					//				not
					//--------------------------------
					case notEqual ->  value != 	operand ;
					
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
