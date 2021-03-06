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

	public BooleanFinder( Class<UDM> udmClass, String operator, String pattern, Var< ? > varObject ) {
		super( udmClass, operator, pattern, null );
		this.fieldId.set( varObject.key() );
	}
	
	public BooleanFinder( Class<UDM> udmClass, String operator, String pattern, Function < Unit < UDM >, Var< ? > > unitFieldGetter ) {
		super( udmClass, operator, pattern, unitFieldGetter );
		this.fieldId.set( getField() );
	}

	@Override
	public  DefaultFinderTransformer< UDM, BooleanFinder< UDM >, Boolean > newTransformer() {
		return new DefaultFinderTransformer<>() {
			@Override 
			public BooleanFinder< UDM > newModel() {
				return new BooleanFinder<>( null, null, null,(Var) null );
			}
		};
	}

    //****************************************************************
    //					FilterAbstract Implements
    //****************************************************************
	
	@Override
	public boolean provePattern( Boolean value) {
		
		var rv = new Var< Boolean >( null,false );
		
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
