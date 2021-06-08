package com.aladdindb.store.models;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DefaultDataModel;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public final class Unit < UDM extends DataModel < UDM > > extends DefaultDataModel < Unit < UDM > > { 
    
    public final Var < String > id      = new Var<>( null, this );
    public final Var < Float > 	version = new Var<>( 1.0f, this );

	public final Meta			meta = new Meta( this );
	
	public final Var < UDM > 	data = new Var< UDM >();
	
    public Unit() {
    	super( null );
	}

    public Unit( UDM data ) {
    	super( null );
    	this.data.set(data);
	}
	
    public Unit( String unitID ) {
    	super( null );
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
