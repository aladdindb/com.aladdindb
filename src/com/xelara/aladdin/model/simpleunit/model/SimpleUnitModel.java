package com.xelara.aladdin.model.simpleunit.model;

import com.xelara.aladdin.unit.model.DbUnitModel;

/**
 *
 * @author Macit Kandemir
 */
public class SimpleUnitModel extends DbUnitModel < SimpleUnitModel > { 
    
	public SimpleUnitModel() {
	}
	
	public SimpleUnitModel( String label ) {
		this.label.setValue( label );
	}

	@Override
	public void fill( SimpleUnitModel unit ) {
		super.fill(unit);
	}
}
