package com.aladdindb.method.req.update;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.store.models.UnitTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UpdateReqTransformer < UDM extends DataModel< UDM > > extends ReqTransformer< UpdateReq< UDM > >  {
	

	public final UnitTransformer< UDM > unitTransformer;
    
    
    //****************************************************************
    //
    //****************************************************************

    public UpdateReqTransformer( Transformer< UDM > unitTransformer ) {
		super( Method.UPDATE.reqTagName() );
		this.unitTransformer = new UnitTransformer<UDM>(unitTransformer);
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
	public UpdateReq< UDM > newModel() {
		return new UpdateReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public UpdateReq< UDM >  toModel( SnPoint src, UpdateReq< UDM > target ) {
    	super.toModel( src, target );
        
        unitTransformer.toModelFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( UpdateReq< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
        
        unitTransformer.toParentNode( src.unitData ,target );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
