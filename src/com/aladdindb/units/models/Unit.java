package com.aladdindb.units.models;

import com.aladdindb.structure.Store;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public final class Unit < UDM extends Store < UDM > > implements Store < Unit < UDM > > { 
    
    public final Var < String > id      = new Var<>();
    public final Var < Float > 	version = new Var<>( 1.0f );

	public final Meta			meta = new Meta();
	public final Var < UDM > 	data = new Var< UDM >();
	
    public Unit() {
	}
	
    public Unit( String unitID ) {
    	this.id.set( unitID );
	}

	@Override
	public void fill( Unit< UDM >  model ) {
		id		.set( model.id			);
		version	.set( model.version	);
		data	.set( model.data		);
	}

	public void incVersion() {
		this.version.get( v -> this.version.set( v+=0.1f ) );
	}
    
}
