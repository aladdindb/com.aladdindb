package com.xelara.aladdin.unit.model;

import com.xelara.structure.snode.SNode;

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
	public UnitListModel < DATA_MODEL > parse( SNode src, UnitListModel < DATA_MODEL > target ) {
    	src.forEachChilds( unode -> {
    		unitModelParser.parse( unode, target :: add );
    	});
		return target ;
	}
	

	@ Override
	public SNode parse( UnitListModel < DATA_MODEL > src, SNode target ) {
    	src.forEach( unit -> {
    		unitModelParser.parse( unit, target :: addChild );
    	});
		return target;
	}
    
    //****************************************************************
    //
    //****************************************************************

}
