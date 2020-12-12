package com.xelara.aladdin.index.model;

import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public class IndexModel implements UnitModel < IndexModel >  {
    
    public final Var < String > refUnitID = new Var<>();
    
    public IndexModel() {
    }
    
    public IndexModel( String refUnitID ) {
		this.refUnitID.setValue( refUnitID );
    }
    
	@Override
	public void fill( IndexModel model ) {
		refUnitID.fill( model.refUnitID );
	}
	
	
}
