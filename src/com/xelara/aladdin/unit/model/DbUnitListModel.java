package com.xelara.aladdin.unit.model;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author Macit Kandemir
 */
public class DbUnitListModel <

	UM 		extends UnitModel 			< UM >

> extends ArrayList < UM > implements UnitModel <  DbUnitListModel < UM >  >  {

	
    public DbUnitListModel() {
    }
    
    public DbUnitListModel( UM... modelArray ) {
        this.addAllUnits( modelArray );
    }
    
    public void addAllUnits( UM... modelArray ) {
    	this.clear();
    	for( UM unit : modelArray ) {
            this.add( unit );
    	}
    }

    public void getFirstItem( Consumer< UM > consumer ) {
    	UM rv = getFirstItem();
    	if( rv != null ) consumer.accept( rv );
    }
    
    public UM getFirstItem() {
		 return this.size() > 0 ?  this.get( 0 ) : null;
    }

    @Override
	public void fill( DbUnitListModel < UM > listModel ) {
    	this.clear();
		listModel.forEach(  this :: add );
	}
    
}
