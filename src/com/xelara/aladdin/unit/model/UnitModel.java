package com.xelara.aladdin.unit.model;

import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public final class UnitModel < DATA_MODEL extends DataModel < DATA_MODEL > > implements DataModel < UnitModel < DATA_MODEL > > { 
    
    public final Var < String > id      	= new Var<>();
    public final Var < String > version   	= new Var<>();

	public final MetaModel			meta = new MetaModel();
	
	public final Var < DATA_MODEL > data = new Var< DATA_MODEL >();
	
    public UnitModel() {
	}
	
    public UnitModel( String unitID, String label ) {
    	this.id			.setValue( unitID	);
    	this.version	.setValue( label	);
	}

	@Override
	public void fill( UnitModel< DATA_MODEL >  model ) {
		id			.fill( model.id			);
		version		.fill( model.version	);
		data		.fill( model.data		);
	}

    
}
