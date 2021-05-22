package com.aladdindb.method.resp.remove;

import com.aladdindb.method.Method;
import com.aladdindb.store.models.UnitTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class RemoveRespTransformer < UDM extends DataModel < UDM > > extends Transformer < RemoveResp < UDM > > {
	

    public final UnitTransformer < UDM > unitTransformer;
    
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveRespTransformer( Transformer < UDM > unitDataTransformer ) {
		super( Method.remove.respTagName() );
		
		this.unitTransformer = new UnitTransformer < UDM > (unitDataTransformer );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveResp< UDM > newModel() {
		return new RemoveResp< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public RemoveResp< UDM >  toModel( SnPoint src, RemoveResp< UDM > target ) {
    
        unitTransformer.toModelFromParent( src ,target.unit );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( RemoveResp< UDM > src, SnPoint target ) {
        
        unitTransformer.toParentNode( src.unit ,target );

        return target;
    }
    
    
    
}
