package com.aladdindb.method.resp.remove;

import com.aladdindb.method.Method;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitTransformer;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class RemoveRespTransformer < UDM extends DataModel < UDM > > extends Transformer < RemoveResp < UDM > > {
	

    public final UnitTransformer < UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public RemoveRespTransformer( Transformer < UDM > unitDataParser ) {
		super( Method.REMOVE.respTagName() );
		
		this.unitParser = new UnitTransformer < UDM > (unitDataParser );
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
