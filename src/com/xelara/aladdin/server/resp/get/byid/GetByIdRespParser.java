package com.xelara.aladdin.server.resp.get.byid;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.server.resp.Resp;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByIdRespParser < UDM extends DataModel< UDM > > extends DataParser < GetByIdResp< UDM > > {
	

    public final UnitParser< UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public GetByIdRespParser( UnitParser< UDM > unitParser ) {
		super( Resp.GET_BY_ID );
		this.unitParser = unitParser;
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
