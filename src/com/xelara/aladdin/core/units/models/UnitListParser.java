package com.xelara.aladdin.core.units.models;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.structure.sn.SnPoint;

/**
 * @author Macit Kandemir
 * 
 * @param <ULM>      
 * @param <UM>      
 * @param <UMP>    
 */
public final class UnitListParser <

	DATA_MODEL extends DataModel < DATA_MODEL > 
    
> extends DataParser < UnitList	< DATA_MODEL > > {
    

	private final UnitParser< DATA_MODEL > unitModelParser;
    
	
    //****************************************************************
    //
    //****************************************************************

    public UnitListParser( DataParser< DATA_MODEL > dataModelParser  ) {
    	this( "units", dataModelParser );
    }

    public UnitListParser( String key, DataParser< DATA_MODEL > dataModelParser  ) {
        super( key );
        this.unitModelParser = new UnitParser< DATA_MODEL >(dataModelParser);
    }
    
    //****************************************************************
    //
    //****************************************************************
    
	@ Override
	public UnitList < DATA_MODEL > newModel() { 
		return new UnitList < DATA_MODEL > (); 
	}
	

	@ Override
	public UnitList < DATA_MODEL > toModel( SnPoint src, UnitList < DATA_MODEL > target ) {
    	src.children.forEach( unode -> {
    		unitModelParser.toModel( unode, target :: add );
    	});
		return target ;
	}
	

	@ Override
	public SnPoint toNode( UnitList < DATA_MODEL > src, SnPoint target ) {
    	src.forEach( unit -> {
    		unitModelParser.toNode( unit, target.children :: add );
    	});
		return target;
	}
    
    //****************************************************************
    //
    //****************************************************************

}
