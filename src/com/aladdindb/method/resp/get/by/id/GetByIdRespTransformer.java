package com.aladdindb.method.resp.get.by.id;

import com.aladdindb.method.Method;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitTransformer;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByIdRespTransformer < UDM extends Store< UDM > > extends Transformer < GetByIdResp< UDM > > {
	

    public final UnitTransformer< UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdRespTransformer( Transformer< UDM > unitDataParser ) {
		super( Method.GET_BY_ID.respTagName() );
		this.unitParser = new UnitTransformer< UDM >(unitDataParser);
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdResp< UDM > newStore() {
		return new GetByIdResp< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public GetByIdResp< UDM >  toStore( SnPoint src, GetByIdResp< UDM > target ) {
    
        unitParser.toStoreFromParent( src ,target.unit );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdResp< UDM > src, SnPoint target ) {
        
        unitParser.toParentNode( src.unit ,target );

        return target;
    }
    
    
    
}
