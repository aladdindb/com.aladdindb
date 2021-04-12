package com.xelara.aladdin.server.resp.remove;

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
public final class RemoveRespParser < UDM extends DataModel< UDM > > extends DataParser < RemoveResp< UDM > > {
	

    public final UnitParser< UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveRespParser( UnitParser< UDM > unitParser ) {
		super( Resp.REMOVE );
		this.unitParser = unitParser;
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
    
        unitParser.toModelFromParent( src ,target.unit );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( RemoveResp< UDM > src, SnPoint target ) {
        
        unitParser.toParentNode( src.unit ,target );

        return target;
    }
    
    
    
}
