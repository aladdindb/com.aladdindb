package com.xelara.aladdin.model.list.input;

import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.core.Var;

public class InputModel implements UnitModel < InputModel > {
	
	public final Var< String > entry = new Var<>();
	
	public InputModel() {
	}
	
	public InputModel( String value ) {
		this.entry.setValue( value );
	}

	public boolean equalsEntry( String value) {
		return this.entry.equalsValue( value );
	}
	
	@Override
	public void fill( InputModel unit ) {
		entry.fill( unit.entry );
	}

    public boolean existPart( String part ) {
    	Var< Boolean > rv = new Var<>( false );
    	entry.getValue( value -> {
    		rv.setValue( value.toLowerCase().indexOf( part.toLowerCase() ) >= 0 );
    	});
    	return rv.getValue();
    }
	
}
