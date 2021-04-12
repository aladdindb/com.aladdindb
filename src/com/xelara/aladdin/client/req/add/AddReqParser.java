package com.xelara.aladdin.client.req.add;

import com.xelara.aladdin.client.req.Req;
import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddReqParser < UDM extends DataModel< UDM > > extends DataParser < AddReq< UDM > > {
	

    private enum ATR { unitGroupID, userID };
    
	
    public final DataParser< UDM > unitDataParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public AddReqParser( DataParser< UDM > unitDataParser ) {
		super( Req.ADD );
		this.unitDataParser = unitDataParser;
	}
    
    //****************************************************************
    //
    //****************************************************************

    public AddReq< UDM > newModel() {
		return new AddReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public AddReq< UDM >  toModel( SnPoint src, AddReq< UDM > target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID	,target.unitGroupID 	);
        
        unitDataParser.toModelFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddReq< UDM > src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID  	,src.unitGroupID	);
        
        unitDataParser.toParentNode( src.unitData ,target );

        return target;
    }
    
    
    
}
