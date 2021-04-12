package com.xelara.aladdin.core.units.models;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UnitParser < UDM extends DataModel < UDM > > extends DataParser < Unit < UDM > > {
	

    private enum ATR { id, version };
	
    private final MetaParser meta = new MetaParser();
    private final DataParser< UDM > data;
    
	public UnitParser( DataParser< UDM > dataModelParser ) {
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
