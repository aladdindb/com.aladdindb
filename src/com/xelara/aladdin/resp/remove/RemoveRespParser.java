package com.xelara.aladdin.resp.remove;

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
public final class RemoveRespParser < UDM extends DataModel < UDM > > extends DataParser < RemoveResp < UDM > > {
	

    public final UnitParser < UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveRespParser( DataParser < UDM > unitDataParser ) {
		super( Cmd.REMOVE.res() );
		
		this.unitParser = new UnitParser < UDM > (unitDataParser );
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
