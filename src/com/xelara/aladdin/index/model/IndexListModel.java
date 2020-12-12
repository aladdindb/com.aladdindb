package com.xelara.aladdin.index.model;

import com.xelara.aladdin.unit.model.DbUnitListModel;
import com.xelara.core.BreakException;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public class IndexListModel  extends DbUnitListModel < IndexModel, IndexListModel > {
    
    public IndexModel find( String refUnitID ) {
    	Var< IndexModel > rv = new Var<>();
    	try {
    		this.forEach( unit -> {
    			if( unit.refUnitID.equalsValue( refUnitID ) ) {
    				rv.setValue( unit );
    				throw new BreakException();
    			}
    		});
    	} catch ( BreakException  e ) {
    		
    	}
    	
    	return rv.getValue();
    }
    
    public IndexModel remove( String refUnitID ) {
    	var rv = find( refUnitID );
    	if( rv != null ) this.remove(rv);
    	return rv;
    }
	
    public void updateItem( String refUnitID ) {
		var oldItem = remove( refUnitID );
		if( oldItem != null ) {
			var newItem = new IndexModel( refUnitID );
			add( newItem );
		}
    }
    
}
