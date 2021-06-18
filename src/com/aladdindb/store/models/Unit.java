package com.aladdindb.store.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DefaultDataModel;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public final class Unit < UDM extends DataModel < UDM > > extends DefaultDataModel < Unit < UDM > > { 

	
    public final Var < String > id      = new Var<>( this );
    public final Var < Float > 	version = new Var<>( this, 1.0f );

	public final Meta			meta = new Meta( this );
	
	public final Var < UDM > 	data = new Var< UDM >( this );
	
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

	public static  Var<?> LABEL() {
		return new Unit<>().meta.label;
	}

	public static  Var<?> GENERATED_ON() {
		return new Unit<>().meta.timeStamp.generatedOn;
	}

	public static  Var<?> MODIFIED_ON() {
		return new Unit<>().meta.timeStamp.modifiedOn;
	}
	
	public static ZonedDateTime dateTime( int year, int month, int day ) {
		return ZonedDateTime.of( year, month, day, 0, 0, 0, 0,ZoneId.systemDefault() );
	}
	
}
