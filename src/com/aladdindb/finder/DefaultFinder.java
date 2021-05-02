package com.aladdindb.finder;

import java.util.function.Consumer;

import com.aladdindb.structure.DataModel;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <FINDER_MODEL>	Der reale Filter Typ.
 * @param <VT>		Filter Pattern Typ.
 */
public abstract class DefaultFinder <

	UDM				extends DataModel 		< UDM >,  
	FINDER_MODEL	extends DefaultFinder	< UDM, FINDER_MODEL, VT >,
	VT
	
> implements Finder < UDM, FINDER_MODEL > {

	
	public final Var < String 	> operator 	= new Var<>();
	public final Var < String	> pattern 	= new Var<>();
	
	
	public DefaultFinder( String operator, String pattern ) {
		this.operator	.set( operator	);
		this.pattern	.set( pattern	);
	}
	
	@Override
	public void fill( FINDER_MODEL model) {
		this.operator	.set( model.operator 	);
		this.pattern	.set( model.pattern		);
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	@Override
	public boolean prove( Unit<UDM> model ) {
		Var<Boolean> rv = new Var<>(false);
		this.getFieldValue( model, value -> {
			rv.set( provePattern( value ) );
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
	
}