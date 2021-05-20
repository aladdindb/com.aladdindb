package com.aladdindb.units.models;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
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
    
> extends Transformer < UnitList	< DATA_MODEL > > {
    

	private final UnitTransformer< DATA_MODEL > unitTransformer;
    
	
    //****************************************************************
    //
    //****************************************************************

    public UnitListTransformer( Transformer< DATA_MODEL > unitDataTransformer  ) {
    	this( "units", unitDataTransformer );
    }

    public UnitListTransformer( String key, Transformer< DATA_MODEL > unitDataTransformer  ) {
        super( key );
        this.unitTransformer = new UnitTransformer< DATA_MODEL >(unitDataTransformer);
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
    		unitTransformer.toModel( unode, target :: add );
    	});
		return target ;
	}
	

	@ Override
	public SnPoint toNode( UnitList < DATA_MODEL > src, SnPoint target ) {
    	src.forEach( unit -> {
    		unitTransformer.toNode( unit, target.children :: add );
    	});
		return target;
	}
    
    //****************************************************************
    //
    //****************************************************************

}
