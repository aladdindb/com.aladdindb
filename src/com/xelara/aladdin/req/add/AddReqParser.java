package com.xelara.aladdin.req.add;

import com.xelara.aladdin.req.Cmd;
import com.xelara.aladdin.req.ReqParser;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddReqParser < UDM extends DataModel< UDM > > extends ReqParser< AddReqModel< UDM > >  {
	

	public final DataParser< UDM > unitDataParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public AddReqParser( DataParser< UDM > unitDataParser ) {
		super( Cmd.ADD.req() );
		this.unitDataParser = unitDataParser;
	}
    
    //****************************************************************
    //
    //****************************************************************

    public AddReqModel< UDM > newModel() {
		return new AddReqModel< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public AddReqModel< UDM >  toModel( SnPoint src, AddReqModel< UDM > target ) {
    	super.toModel( src, target );
        
        unitDataParser.toModelFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddReqModel< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
        
        unitDataParser.toParentNode( src.unitData ,target );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
