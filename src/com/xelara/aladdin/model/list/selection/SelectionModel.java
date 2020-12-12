package com.xelara.aladdin.model.list.selection;

import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.core.Var;

public class SelectionModel implements UnitModel < SelectionModel > {
	
	public final Var< String > refUnitID = new Var<>();
	
	public SelectionModel() {
	}
	
	public SelectionModel( String refUnitID ) {
		this.refUnitID.setValue( refUnitID );
	}

	public boolean equalsRefUnitID( String refUnitID) {
		return this.refUnitID.equalsValue( refUnitID );
	}
	
	@Override
	public void fill( SelectionModel model ) {
		refUnitID.fill( model.refUnitID );
	}

}
