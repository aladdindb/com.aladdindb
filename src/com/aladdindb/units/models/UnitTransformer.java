package com.aladdindb.units.models;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UnitTransformer < UDM extends DataModel < UDM > > extends DataTransformer < Unit < UDM > > {
	

    private enum ATR { id, version };
	
    private final MetaTransformer meta = new MetaTransformer();
    
    private final DataTransformer< UDM > dataTransformer;
    
	public UnitTransformer( DataTransformer< UDM > dataTransformer ) {
		super("unit");
		this.dataTransformer = dataTransformer;
	}
    
	@Override
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
        
        this.meta				.toModelFromParent( src	, target.meta );
        
        if( this.dataTransformer != null) this.dataTransformer	.toModelFromParent( src	, target.data );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( Unit< UDM > src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr		.set( ATR.id      	, src.id 		); 
    	targetAtr.asFloat	.set( ATR.version   , src.version	);
        
        this.meta				.toParentNode( src.meta, target );
        
        if( this.dataTransformer != null) this.dataTransformer	.toParentNode( src.data, target );

        return target; 
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
