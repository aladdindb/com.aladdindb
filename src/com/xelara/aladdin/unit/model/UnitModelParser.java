package com.xelara.aladdin.unit.model;

import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

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
    public UnitModel< DATA_MODEL >  parse( SNode src, UnitModel< DATA_MODEL >  target ) {
    
        Parser.STR	.parse( ATR.id		,src 	,target.id      	);
        Parser.STR	.parse( ATR.version	,src  	,target.version   	);
        
        this.meta.parseFromParent( src, target.meta );
        this.data.parseFromParent( src, target.data );
        
        return target;
    }
    
    @Override
    public SNode parse( UnitModel< DATA_MODEL > src, SNode target ) {
        
        Parser.STR	.parse( ATR.id      	, src.id    	,target );
        Parser.STR	.parse( ATR.version   	, src.version 	,target );
        
        this.meta.parseToParent( src.meta, target );
        this.data.parseToParent( src.data, target );

        target.setValueType( SN.VALUE_TYPE_CHILDREN );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
