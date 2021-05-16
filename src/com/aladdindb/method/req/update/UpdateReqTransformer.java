package com.aladdindb.method.req.update;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitTransformer;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class UpdateReqTransformer < UDM extends DataModel< UDM > > extends ReqTransformer< UpdateReq< UDM > >  {
	

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
	public UpdateReq< UDM > newModel() {
		return new UpdateReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public UpdateReq< UDM >  toModel( SnPoint src, UpdateReq< UDM > target ) {
    	super.toModel( src, target );
        
        unitParser.toModelFromParent( src ,target.unitData );
        
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
