package com.aladdindb.method.resp.get.by.id;

import com.aladdindb.method.Method;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitTransformer;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByIdRespTransformer < UDM extends DataModel< UDM > > extends DataTransformer < GetByIdResp< UDM > > {
	

    public final UnitTransformer< UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdRespTransformer( DataTransformer< UDM > unitDataParser ) {
		super( Method.GET_BY_ID.respTagName() );
		this.unitParser = new UnitTransformer< UDM >(unitDataParser);
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
    
        unitParser.toModelFromParent( src ,target.unit );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdResp< UDM > src, SnPoint target ) {
        
        unitParser.toParentNode( src.unit ,target );

        return target;
    }
    
    
    
}
