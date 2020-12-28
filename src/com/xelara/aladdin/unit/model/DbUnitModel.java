package com.xelara.aladdin.unit.model;

import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public abstract class DbUnitModel < DUM extends DbUnitModel < DUM > > implements UnitModel < DUM > { 
    
    public final Var < String > id      	= new Var<>();
    public final Var < String > label   	= new Var<>();
    public final Var < String > tsNew  		= new Var<>();
    public final Var < String > tsUpdate	= new Var<>();
 
    public DbUnitModel() {
    	this( null, null );
	}
    
    public DbUnitModel( String unitID, String label ) {
    	this.id		.setValue( unitID	);
    	this.label	.setValue( label	);
	}

	@Override
	public void fill( DUM  model ) {
		id			.fill( model.id		);
		label		.fill( model.label	);
		tsNew		.fill( model.tsNew	);
		tsUpdate	.fill( model.tsUpdate	);
	}

    @Override
    public String toString() {
    	return this.label.getValue();
    }
    
    
}
