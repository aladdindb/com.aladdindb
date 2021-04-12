package com.xelara.aladdin.client.req.update;

import com.xelara.aladdin.client.req.Req;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UpdateReqParser < UDM extends DataModel< UDM > > extends DataParser < UpdateReq< UDM > > {
	

    private enum ATR { unitGroupID, userID };
    
	
    public final UnitParser< UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public UpdateReqParser( DataParser< UDM > unitDataParser ) {
		super( Req.UPDATE );
		this.unitParser = new UnitParser<UDM>(unitDataParser);
	} 
    
    //****************************************************************
    //
    //****************************************************************

    public UpdateReq< UDM > newModel() {
		return new UpdateReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public UpdateReq< UDM >  toModel( SnPoint src, UpdateReq< UDM > target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID	,target.unitGroupID 	);
        
        unitParser.toModelFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( UpdateReq< UDM > src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID  	,src.unitGroupID	);
        
        unitParser.toParentNode( src.unitData ,target );

        return target;
    }
    
    
    
}
