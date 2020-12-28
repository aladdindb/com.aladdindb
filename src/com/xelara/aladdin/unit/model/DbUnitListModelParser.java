package com.xelara.aladdin.unit.model;

import com.xelara.structure.snode.SNode;

/**
 * @author Macit Kandemir
 * 
 * @param <ULM>      
 * @param <UM>      
 * @param <UMP>    
 */
public abstract class DbUnitListModelParser <

	UM 		extends UnitModel 			< UM >, 
    UMP 	extends UnitModelParser 	< UM >
    
> extends UnitModelParser < DbUnitListModel	< UM > > {
    

	private final UMP unitModelParser;
    
	
    //****************************************************************
    //
    //****************************************************************

    public DbUnitListModelParser( UMP unitModelParser  ) {
    	this( "Units", unitModelParser );
    }

    public DbUnitListModelParser( String key, UMP unitModelParser  ) {
        super( key );
        this.unitModelParser = unitModelParser;
    }
    
    //****************************************************************
    //
    //****************************************************************

    public void parseList( SNode src, DbUnitListModel< UM > target ) {
    	src.forEachChilds( unode -> {
    		unitModelParser.parse( unode, target :: add );
    	});
    }
    
    public void parseList( DbUnitListModel< UM >  src, SNode target ) {
    	src.forEach( unit -> {
    		unitModelParser.parse( unit, target :: addChild );
    	});
    }
    
    //****************************************************************
    //
    //****************************************************************

}
