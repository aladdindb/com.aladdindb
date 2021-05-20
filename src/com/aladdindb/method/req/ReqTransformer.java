package com.aladdindb.method.req;

import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

public abstract class ReqTransformer < DATA_MODEL extends Req< DATA_MODEL> >  extends Transformer< DATA_MODEL > {  
 

    public enum ATR { storeId };
	
    //****************************************************************
    //						Constructors
    //****************************************************************
	
	public ReqTransformer( String nodeKey ) {
		
		super( nodeKey );
	}

    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	
    @Override
    public DATA_MODEL toModel( SnPoint src, DATA_MODEL target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.storeId ,target.storeId );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( DATA_MODEL src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.storeId ,src.storeId );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
}
