package com.xelara.aladdin.model.simpleunit;

import java.nio.file.Path;

import com.xelara.aladdin.index.IndexOrigin;
import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitModel;
import com.xelara.aladdin.model.simpleunit.model.SimpleUnitModelParser;
import com.xelara.aladdin.unit.DbUnits;
import com.xelara.core.Var;

/**
 *
 * @author Macit
 */
public class SimpleUnit  extends DbUnits < SimpleUnitModel, SimpleUnitModelParser > {
    
	public final WishModel wish;
	private IndexOrigin indexes = null;
	
	public SimpleUnit( Path dbPath, WishModel wishModel, IndexOrigin indexes ) {
        super( dbPath, new SimpleUnitModelParser() );
        this.wish = wishModel;
        this.indexes = indexes;
    }
	
	@Override
	public boolean removeUnit( String unitID ) {
		Var<Boolean> rv = new Var<Boolean>( false );
		indexes.getIndex( unitID, index -> {
			//Nur entfernen wenn keine Refferenzen mehr vorhanden sind
			if( index.isEmpty()) {
				rv.setValue( super.removeUnit(unitID) );
			} 
		});
		return rv.getValue();
	}
	
}
