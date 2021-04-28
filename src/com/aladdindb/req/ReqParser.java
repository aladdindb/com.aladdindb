package com.aladdindb.req;

import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

public abstract class ReqParser < DATA_MODEL extends ReqModel< DATA_MODEL> >  extends DataParser< DATA_MODEL > {  


    private enum ATR { unitGroupID };
	
    //****************************************************************
    //						Constructors
    //****************************************************************
	
	public ReqParser( String nodeKey ) {
		
		super( nodeKey );
	}

    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	
    @Override
    public DATA_MODEL toModel( SnPoint src, DATA_MODEL target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID ,target.unitGroupID );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( DATA_MODEL src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID ,src.unitGroupID );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
}
