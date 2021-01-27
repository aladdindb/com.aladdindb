package com.xelara.aladdin.unit.model;

import com.xelara.structure.node.Snode;
import com.xelara.structure.attributes.AParser;
import com.xelara.structure.node.SnValueType;

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
    public UnitModel< DATA_MODEL >  fromNode( Snode node, UnitModel< DATA_MODEL >  model ) {
    
    	var parse = new AParser( node );

    	parse.strPrs	.get( ATR.id		, model.id     	);
    	parse.strPrs	.get( ATR.version	, model.version );
        
        this.meta.fromParentNode( node	, model.meta );
        this.data.fromParentNode( node	, model.data );
        
        return model;
    }
    
    @Override
    public Snode toNode( UnitModel< DATA_MODEL > model, Snode node ) {
        
    	var parse = new AParser( node );

    	parse.strPrs	.set( ATR.id      	, model.id 		); 
    	parse.strPrs	.set( ATR.version   , model.version	);
        
        this.meta.toParentNode( model.meta, node );
        this.data.toParentNode( model.data, node );

        node.setValueType( SnValueType.CHILDREN );
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
