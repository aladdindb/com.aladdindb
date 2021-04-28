package com.aladdindb.resp.get.by.id;

import com.aladdindb.Method;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitParser;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByIdRespParser < UDM extends DataModel< UDM > > extends DataParser < GetByIdRespModel< UDM > > {
	

    public final UnitParser< UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdRespParser( DataParser< UDM > unitDataParser ) {
		super( Method.GET_BY_ID.res() );
		this.unitParser = new UnitParser< UDM >(unitDataParser);
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdRespModel< UDM > newModel() {
		return new GetByIdRespModel< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public GetByIdRespModel< UDM >  toModel( SnPoint src, GetByIdRespModel< UDM > target ) {
    
        unitParser.toModelFromParent( src ,target.unit );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByIdRespModel< UDM > src, SnPoint target ) {
        
        unitParser.toParentNode( src.unit ,target );

        return target;
    }
    
    
    
}
