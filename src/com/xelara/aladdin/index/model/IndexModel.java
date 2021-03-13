package com.xelara.aladdin.index.model;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class IndexModel implements DataModel < IndexModel >  {
    
    public final Var < String > refUnitID = new Var<>();
    
    public IndexModel() {
    }
    
    public IndexModel( String refUnitID ) {
		this.refUnitID.set( refUnitID );
    }
    
	@Override
	public void fill( IndexModel model ) {
		refUnitID.fill( model.refUnitID );
	}
	
	
}
