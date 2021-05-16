package com.aladdindb.method.req.update;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitTransformer;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UpdateReqTransformer < UDM extends Store< UDM > > extends ReqTransformer< UpdateReq< UDM > >  {
	

	public final UnitTransformer< UDM > unitParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public UpdateReqTransformer( Transformer< UDM > unitDataParser ) {
		super( Method.UPDATE.reqTagName() );
		this.unitParser = new UnitTransformer<UDM>(unitDataParser);
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
	public UpdateReq< UDM > newStore() {
		return new UpdateReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public UpdateReq< UDM >  toStore( SnPoint src, UpdateReq< UDM > target ) {
    	super.toStore( src, target );
        
        unitParser.toStoreFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( UpdateReq< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
        
        unitParser.toParentNode( src.unitData ,target );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
