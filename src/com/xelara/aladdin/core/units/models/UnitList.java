package com.xelara.aladdin.core.units.models;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.xelara.aladdin.core.DataModel;

/**
 *
 * @author Macit Kandemir
 */
public final class UnitList <

	DATA_MODEL extends DataModel < DATA_MODEL > 

> extends ArrayList < Unit < DATA_MODEL > > implements DataModel <  UnitList < DATA_MODEL >  >  {

	
    public UnitList() {
    }
    
    public UnitList( Unit < DATA_MODEL >... modelArray ) {
        this.addAllUnits( modelArray );
    }
    
    public void addAllUnits( Unit < DATA_MODEL >... modelArray ) {
    	this.clear();
    	for( Unit < DATA_MODEL > unit : modelArray ) {
            this.add( unit );
    	}
    }

    public void getFirstItem( Consumer< Unit < DATA_MODEL > > consumer ) {
    	Unit < DATA_MODEL > rv = getFirstItem();
    	if( rv != null ) consumer.accept( rv );
    }
    
    public Unit < DATA_MODEL > getFirstItem() {
		 return this.size() > 0 ?  this.get( 0 ) : null;
    }

    @Override
	public void fill( UnitList < DATA_MODEL >  listModel ) {
    	this.clear();
		listModel.forEach(  this :: add );
	}
    
}
