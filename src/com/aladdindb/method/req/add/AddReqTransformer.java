package com.aladdindb.method.req.add;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddReqTransformer < UDM extends DataModel< UDM > > extends ReqTransformer< AddReq< UDM > >  {
	

	public final Transformer< UDM > dataTransformer;
    
    
    //****************************************************************
    //
    //****************************************************************

    public AddReqTransformer( Transformer< UDM > dataTransformer ) {
		super( Method.ADD.reqTagName() );
		this.dataTransformer = dataTransformer;
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
	public AddReq< UDM > newModel() {
		return new AddReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public AddReq< UDM >  toModel( SnPoint src, AddReq< UDM > target ) {
    	super.toModel( src, target );
        
        dataTransformer.toModelFromParent( src ,target.unitData );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddReq< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
        
        dataTransformer.toParentNode( src.unitData ,target );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
