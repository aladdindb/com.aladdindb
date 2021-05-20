package com.aladdindb.method.resp.get.by.id;

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
public final class GetByIdRespTransformer < UDM extends DataModel< UDM > > extends Transformer < GetByIdResp< UDM > > {
	

    public final UnitTransformer< UDM > unitTransformer;
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdRespTransformer( Transformer< UDM > unitDataTransformer ) {
		super( Method.GET_BY_ID.respTagName() );
		this.unitTransformer = new UnitTransformer< UDM >(unitDataTransformer);
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdResp< UDM > newModel() {
		return new GetByIdResp< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public GetByIdResp< UDM >  toModel( SnPoint src, GetByIdResp< UDM > target ) {
    
        unitTransformer.toModelFromParent( src ,target.unit );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdResp< UDM > src, SnPoint target ) {
        
        unitTransformer.toParentNode( src.unit ,target );

        return target;
    }
    
    
    
}
