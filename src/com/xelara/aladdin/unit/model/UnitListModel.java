package com.xelara.aladdin.unit.model;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author Macit Kandemir
 */
public final class UnitListModel <

	DATA_MODEL extends DataModel < DATA_MODEL > 

> extends ArrayList < UnitModel < DATA_MODEL > > implements DataModel <  UnitListModel < DATA_MODEL >  >  {

	
    public UnitListModel() {
    }
    
    public UnitListModel( UnitModel < DATA_MODEL >... modelArray ) {
        this.addAllUnits( modelArray );
    }
    
    public void addAllUnits( UnitModel < DATA_MODEL >... modelArray ) {
    	this.clear();
    	for( UnitModel < DATA_MODEL > unit : modelArray ) {
            this.add( unit );
    	}
    }

    public void getFirstItem( Consumer< UnitModel < DATA_MODEL > > consumer ) {
    	UnitModel < DATA_MODEL > rv = getFirstItem();
    	if( rv != null ) consumer.accept( rv );
    }
    
    public UnitModel < DATA_MODEL > getFirstItem() {
		 return this.size() > 0 ?  this.get( 0 ) : null;
    }

    @Override
	public void fill( UnitListModel < DATA_MODEL >  listModel ) {
    	this.clear();
		listModel.forEach(  this :: add );
	}
    
}
