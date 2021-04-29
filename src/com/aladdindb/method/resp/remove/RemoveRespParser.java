package com.aladdindb.method.resp.remove;

import com.aladdindb.method.Method;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitParser;

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
		super( Method.REMOVE.res() );
		
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
