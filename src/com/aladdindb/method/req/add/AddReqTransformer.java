package com.aladdindb.method.req.add;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddReqTransformer < UDM extends Store< UDM > > extends ReqTransformer< AddReq< UDM > >  {
	

	public final Transformer< UDM > unitDataParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public AddReqTransformer( Transformer< UDM > unitDataParser ) {
		super( Method.ADD.reqTagName() );
		this.unitDataParser = unitDataParser;
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
	public AddReq< UDM > newStore() {
		return new AddReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public AddReq< UDM >  toStore( SnPoint src, AddReq< UDM > target ) {
    	super.toStore( src, target );
        
        unitDataParser.toStoreFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddReq< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
        
        unitDataParser.toParentNode( src.unitData ,target );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
