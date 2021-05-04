package com.aladdindb.units.models;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;

/**
 * @author Macit Kandemir
 * 
 * @param <ULM>      
 * @param <UM>      
 * @param <UMP>    
 */
public final class UnitListTransformer <

	DATA_MODEL extends DataModel < DATA_MODEL > 
    
> extends DataTransformer < UnitList	< DATA_MODEL > > {
    

	private final UnitTransformer< DATA_MODEL > unitModelParser;
    
	
    //****************************************************************
    //
    //****************************************************************

    public UnitListTransformer( DataTransformer< DATA_MODEL > dataModelParser  ) {
    	this( "units", dataModelParser );
    }

    public UnitListTransformer( String key, DataTransformer< DATA_MODEL > dataModelParser  ) {
        super( key );
        this.unitModelParser = new UnitTransformer< DATA_MODEL >(dataModelParser);
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
