package com.xelara.aladdin.unit.model;

import com.xelara.structure.sn.SnPoint;

/**
 * @author Macit Kandemir
 * 
 * @param <ULM>      
 * @param <UM>      
 * @param <UMP>    
 */
public final class UnitListModelParser <

	DATA_MODEL extends DataModel < DATA_MODEL > 
    
> extends DataModelParser < UnitListModel	< DATA_MODEL > > {
    

	private final UnitModelParser< DATA_MODEL > unitModelParser;
    
	
    //****************************************************************
    //
    //****************************************************************

    public UnitListModelParser( DataModelParser< DATA_MODEL > dataModelParser  ) {
    	this( "units", dataModelParser );
    }

    public UnitListModelParser( String key, DataModelParser< DATA_MODEL > dataModelParser  ) {
        super( key );
        this.unitModelParser = new UnitModelParser< DATA_MODEL >(dataModelParser);
    }
    
    //****************************************************************
    //
    //****************************************************************
    
	@ Override
	public UnitListModel < DATA_MODEL > newModel() { 
		return new UnitListModel < DATA_MODEL > (); 
	}
	

	@ Override
	public UnitListModel < DATA_MODEL > fromNode( SnPoint src, UnitListModel < DATA_MODEL > target ) {
    	src.children.forEach( unode -> {
    		unitModelParser.fromNode( unode, target :: add );
    	});
		return target ;
	}
	

	@ Override
	public SnPoint toNode( UnitListModel < DATA_MODEL > src, SnPoint target ) {
    	src.forEach( unit -> {
    		unitModelParser.toNode( unit, target.children :: add );
    	});
		return target;
	}
    
    //****************************************************************
    //
    //****************************************************************

}
