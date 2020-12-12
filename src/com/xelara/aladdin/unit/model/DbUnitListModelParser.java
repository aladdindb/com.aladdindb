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
    ULM 	extends DbUnitListModel		< UM, ULM >, 
    UMP 	extends UnitModelParser 	< UM >
    
> extends UnitModelParser < ULM > {
    

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

    public void parseList( SNode src, ULM target ) {
    	src.forEachChilds( unode -> {
    		unitModelParser.parse( unode, target :: add );
    	});
    }
    
    public void parseList( ULM  src, SNode target ) {
    	src.forEach( unit -> {
    		unitModelParser.parse( unit, target :: addChild );
    	});
    }
    
    //****************************************************************
    //
    //****************************************************************

}
