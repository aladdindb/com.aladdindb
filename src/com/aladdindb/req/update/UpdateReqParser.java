package com.aladdindb.req.update;

import com.aladdindb.Method;
import com.aladdindb.req.ReqParser;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.UnitParser;

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
		super( Method.UPDATE.req() );
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
