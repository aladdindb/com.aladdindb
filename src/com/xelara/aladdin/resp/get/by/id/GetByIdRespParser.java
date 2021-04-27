package com.xelara.aladdin.resp.get.by.id;

import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.req.Cmd;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;

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
		super( Cmd.GET_BY_ID.res() );
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
