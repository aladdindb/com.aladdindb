package com.xelara.aladdin.unit.model;

import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UnitModelParser <

	DATA_MODEL extends DataModel < DATA_MODEL >

> extends DataModelParser < UnitModel < DATA_MODEL > > {
	

    private enum ATR { id, version };
	
    private final MetaModelParser 				meta = new MetaModelParser();
    private final DataModelParser< DATA_MODEL > data;
    
	public UnitModelParser( DataModelParser< DATA_MODEL > dataModelParser ) {
		super("unit");
		this.data = dataModelParser;
	}
    
	public UnitModel < DATA_MODEL > newModel() {
		return new UnitModel< DATA_MODEL >();
	}
	
    //****************************************************************
    //
    //****************************************************************

    @Override
    public UnitModel< DATA_MODEL >  toModel( SnPoint snPoint, UnitModel< DATA_MODEL >  model ) {
    
    	var parse = new SnAttributeAccess( snPoint );

    	parse.asStr	.get( ATR.id		, model.id     	);
    	parse.asStr	.get( ATR.version	, model.version );
        
        this.meta.toModelFromParent( snPoint	, model.meta );
        this.data.toModelFromParent( snPoint	, model.data );
        
        return model;
    }
    
    @Override
    public SnPoint toNode( UnitModel< DATA_MODEL > model, SnPoint snPoint ) {
        
    	var parse = new SnAttributeAccess( snPoint );

    	parse.asStr	.set( ATR.id      	, model.id 		); 
    	parse.asStr	.set( ATR.version   , model.version	);
        
        this.meta.toParentNode( model.meta, snPoint );
        this.data.toParentNode( model.data, snPoint );

//        node.valueType.set( SnValueType.CHILDREN );
        
        return snPoint;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
