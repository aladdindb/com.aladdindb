package com.aladdindb.store.models;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.XTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;
import com.aladdindb.structure.xml.XML;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UnitTransformer < UDM extends DataModel < UDM > > extends Transformer < Unit < UDM > > {
	

    private enum ATR { id, version };
	
    private final MetaTransformer meta = new MetaTransformer();
    
    private final Class< UDM > udmClass;
    
	public UnitTransformer( Class< UDM > udmClass ) {
		super("Unit");
		this.udmClass = udmClass;
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
        
        this.meta.toModelFromParent( src	, target.meta );

        if( this.udmClass != null ) {
            var dataNode = src.children.get( this.udmClass.getSimpleName() );
    		var t = new XTransformer< UDM >( this.udmClass );
    		t.toModel(dataNode, dataModel-> {
    			target.data.set(dataModel);
    		});
        }
        
        return target;
    }
    
    @Override
    public SnPoint toNode( Unit< UDM > src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr		.set( ATR.id      	, src.id 		); 
    	targetAtr.asFloat	.set( ATR.version   , src.version	);
        
        this.meta.toParentNode( src.meta, target );
        
        if( this.udmClass != null ) {
    		var t = new XTransformer< UDM >( this.udmClass );
    		src.data.get( dataModel -> {
        		t.toNode( dataModel, dataNode -> {
        			target.children.add(dataNode);
        		});
    		});
        }

        return target; 
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
