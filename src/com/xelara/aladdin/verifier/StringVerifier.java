package com.xelara.aladdin.verifier;

import java.util.function.Consumer;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.Var;

public abstract class StringVerifier < DATA_MODEL extends DataModel<DATA_MODEL> > implements Verifier< DATA_MODEL > {

	private final String operator;
	private final String value;
	
	public StringVerifier( String operator, String value ) {
		this.operator 	= operator;
		this.value 		= value;
	}
	
	@Override
	public boolean prove( DATA_MODEL model ) {
		Var<Boolean> rv = new Var<Boolean>( false );
		this.getFieldValue( model, value -> {
			switch( this.operator.trim().toLowerCase() ) {
				case "=="	: rv.set( value.equals( this.value ) ); break;
				case "like"	: break;
				case ">"	: rv.set( value.compareToIgnoreCase( this.value ) > 	0 );break;
				case ">="	: rv.set( value.compareToIgnoreCase( this.value ) >= 	0 );break;
				case "<"	: rv.set( value.compareToIgnoreCase( this.value ) < 	0 );break;
				case "<="	: rv.set( value.compareToIgnoreCase( this.value ) <= 	0 );break;
			}
		});
		return rv.get();
	}
	
	public void getFieldValue ( DATA_MODEL model, Consumer< String > consumer ) {
		var field = this.getField(model);
		if( field != null) {
			field.get( consumer );
		}
	}
	
	public abstract Var< String > getField( DATA_MODEL model );
	

}
