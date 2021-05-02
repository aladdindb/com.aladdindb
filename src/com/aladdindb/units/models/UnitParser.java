package com.aladdindb.units.models;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UnitParser < UDM extends DataModel < UDM > > extends DataTransformer < Unit < UDM > > {
	

    private enum ATR { id, version };
	
    private final MetaParser meta = new MetaParser();
    private final DataTransformer< UDM > data;
    
	public UnitParser( DataTransformer< UDM > dataModelParser ) {
		super("unit");
		this.data = dataModelParser;
	}
    
	public Unit < UDM > newModel() {
		return new Unit< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************

    @Override
    public Unit< UDM >  toModel( SnPoint src, Unit< UDM > target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.id		, target.id     	);
    	srcAtr.asFloat	.get( ATR.version	, target.version );
        
        this.meta.toModelFromParent( src	, target.meta );
        this.data.toModelFromParent( src	, target.data );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( Unit< UDM > src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr		.set( ATR.id      	, src.id 		); 
    	targetAtr.asFloat	.set( ATR.version   , src.version	);
        
        this.meta.toParentNode( src.meta, target );
        this.data.toParentNode( src.data, target );

        return target; 
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
