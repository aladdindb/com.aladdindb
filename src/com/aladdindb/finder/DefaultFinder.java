package com.aladdindb.finder;

import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class DefaultFinder <

	UDM				extends DataModel 		< UDM >,  
	FINDER_MODEL	extends DefaultFinder	< UDM, FINDER_MODEL, VT >,
	VT
	
> implements Finder < UDM, FINDER_MODEL > {

	
	public final Var < String 	> operator 	= new Var<>();
	public final Var < String	> pattern 	= new Var<>();
	
	public final Function < Unit < UDM >, Var< VT > > unitFieldGetter;
	
	
    //****************************************************************
    //					Constructors  
    //****************************************************************
	
	public DefaultFinder( String operator, String pattern, Function < Unit < UDM >, Var< VT > > unitFieldGetter ) {
		var op = createOp( operator );
		this.operator	.set( op != null ? op.name() : null );
		this.pattern	.set( pattern	);
		this.unitFieldGetter = unitFieldGetter;
	}
	
    //****************************************************************
    //				DataModel Implements  
    //****************************************************************

	@Override
	public void fill( FINDER_MODEL model) {
		this.operator	.set( model.operator 	);
		this.pattern	.set( model.pattern		);
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	@Override
	public boolean prove( Unit<UDM> unit ) {
		Var<Boolean> rv = new Var<>(false);
		this.getFieldValue( unit, fieldValue -> {
			rv.set( provePattern( fieldValue ) );
		});
		return rv.get();
	}
	
	public abstract boolean provePattern( VT value );
	
    //****************************************************************
    //					
    //****************************************************************
	
	
	public void getFieldValue ( Unit<UDM> model, Consumer< VT > consumer ) {
		var field = this.unitFieldGetter.apply(model);
		if( field != null) {
			field.get( consumer );
		}
	}
	
//	public abstract Var< VT > getField( Unit<UDM> model );


    //****************************************************************
    //
    //****************************************************************
	
	public static OP createOp( String op ) {
		if( op != null ) {
			return	 op.equals( OP.matches				.real() ) ? OP.matches
					:op.equals( OP.equal				.real()	) ? OP.equal
							
					:op.equals( OP.greater				.real()	) ? OP.greater 
					:op.equals( OP.greaterOrEqual		.real()	) ? OP.greaterOrEqual
							
					:op.equals( OP.less					.real()	) ? OP.less 
					:op.equals( OP.lessOrEqual			.real()	) ? OP.lessOrEqual
					//--------------------------------
					//			Not	
					//--------------------------------
					:op.equals( OP.notMatches			.real() ) ? OP.notMatches
					:op.equals( OP.notEqual				.real()	) ? OP.notEqual
							
					:op.equals( OP.notGreater			.real()	) ? OP.notGreater 
					:op.equals( OP.notGreaterOrEqual	.real()	) ? OP.notGreaterOrEqual
							
					:op.equals( OP.notLess				.real()	) ? OP.notLess 
					:op.equals( OP.notLessOrEqual		.real()	) ? OP.notLessOrEqual 		: OP.matches;
		} else return null; 
	}
}

