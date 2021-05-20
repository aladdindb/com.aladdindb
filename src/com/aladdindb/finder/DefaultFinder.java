package com.aladdindb.finder;

import java.util.function.Consumer;

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
	
    //****************************************************************
    //					Constructors  
    //****************************************************************
	
	public DefaultFinder( String operator, String pattern ) {
		var op = createOp( operator );
		this.operator	.set( op != null ? op.name() : null );
		this.pattern	.set( pattern	);
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
		var field = this.getField(model);
		if( field != null) {
			field.get( consumer );
		}
	}
	
	public abstract Var< VT > getField( Unit<UDM> model );


    //****************************************************************
    //
    //****************************************************************
	
	public static OP createOp( String op ) {
		if( op != null ) {
			return	 op.equals( OP.MATCHES				.real() ) ? OP.MATCHES
					:op.equals( OP.EQUAL				.real()	) ? OP.EQUAL
							
					:op.equals( OP.GREATER				.real()	) ? OP.GREATER 
					:op.equals( OP.GREATER_OR_EQUAL		.real()	) ? OP.GREATER_OR_EQUAL
							
					:op.equals( OP.LESS					.real()	) ? OP.LESS 
					:op.equals( OP.LESS_OR_EQUAL		.real()	) ? OP.LESS_OR_EQUAL
					//--------------------------------
					//			Not	
					//--------------------------------
					:op.equals( OP.NOT_MATCHES			.real() ) ? OP.NOT_MATCHES
					:op.equals( OP.NOT_EQUAL			.real()	) ? OP.NOT_EQUAL
							
					:op.equals( OP.NOT_GREATER			.real()	) ? OP.NOT_GREATER 
					:op.equals( OP.NOT_GREATER_OR_EQUAL	.real()	) ? OP.NOT_GREATER_OR_EQUAL
							
					:op.equals( OP.NOT_LESS				.real()	) ? OP.NOT_LESS 
					:op.equals( OP.NOT_LESS_OR_EQUAL	.real()	) ? OP.NOT_LESS_OR_EQUAL 		: OP.MATCHES;
		} else return null; 
	}
}

