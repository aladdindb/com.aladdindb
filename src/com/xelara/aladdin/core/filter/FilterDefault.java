package com.xelara.aladdin.core.filter;

import java.util.function.Consumer;

import com.xelara.aladdin.core.units.models.Unit;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;


/**
 * 
 * @author Macit Kandemir
 *
 * @param <UDM>		Unit Daten Model Typ, der zu filternden Daten.
 * @param <MODEL>	Der reale Filter Typ.
 * @param <VT>		Filter Pattern Typ.
 */
public abstract class FilterDefault <

	UDM		extends DataModel 		< UDM >,  
	MODEL	extends FilterDefault	< UDM, MODEL, VT >,
	VT
	
> implements Filter < UDM, MODEL > {

	
	public final Var < String 	> operator 	= new Var<>();
	public final Var < String	> pattern 	= new Var<>();
	
	
	public FilterDefault( String operator, String pattern ) {
		this.operator	.set( operator	);
		this.pattern	.set( pattern	);
	}
	
	@Override
	public void fill( MODEL model) {
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
