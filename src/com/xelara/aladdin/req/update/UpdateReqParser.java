package com.xelara.aladdin.req.update;

import com.xelara.aladdin.core.DataModel;
import com.xelara.aladdin.core.DataParser;
import com.xelara.aladdin.core.units.models.UnitParser;
import com.xelara.aladdin.req.Req;
import com.xelara.aladdin.req.ReqParser;
import com.xelara.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UpdateReqParser < UDM extends DataModel< UDM > > extends ReqParser< UpdateReqModel< UDM > >  {
	

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

    public UpdateReqModel< UDM > newModel() {
		return new UpdateReqModel< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public UpdateReqModel< UDM >  toModel( SnPoint src, UpdateReqModel< UDM > target ) {
    	super.toModel( src, target );
        
        unitParser.toModelFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( UpdateReqModel< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
        
        unitParser.toParentNode( src.unitData ,target );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
