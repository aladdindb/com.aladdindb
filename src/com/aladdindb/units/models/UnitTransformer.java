package com.aladdindb.units.models;

import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UnitTransformer < UDM extends Store < UDM > > extends Transformer < Unit < UDM > > {
	

    private enum ATR { id, version };
	
    private final MetaTransformer meta = new MetaTransformer();
    
    private final Transformer< UDM > dataTransformer;
    
	public UnitTransformer( Transformer< UDM > dataTransformer ) {
		super("unit");
		this.dataTransformer = dataTransformer;
	}
    
	@Override
	public Unit < UDM > newStore() {
		return new Unit< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************

    @Override
    public Unit< UDM >  toStore( SnPoint src, Unit< UDM > target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr	.get( ATR.id		, target.id     	);
    	srcAtr.asFloat	.get( ATR.version	, target.version );
        
        this.meta				.toStoreFromParent( src	, target.meta );
        
        if( this.dataTransformer != null) this.dataTransformer	.toStoreFromParent( src	, target.data );
        
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
