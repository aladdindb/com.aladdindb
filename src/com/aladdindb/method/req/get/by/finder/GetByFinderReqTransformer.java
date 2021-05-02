package com.aladdindb.method.req.get.by.finder;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupplier;
import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class GetByFinderReqTransformer <

	UDM 			extends DataModel	< UDM >,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL>

>  extends ReqTransformer< GetByFinderReq < UDM, FINDER_MODEL > >  {
	

    private enum ATR { blockSize };
    
    private final FinderSupplier< UDM > finderSupplier; 
    
	public GetByFinderReqTransformer( FinderSupplier< UDM > finderSupplier ) {
		super( Method.GET_BY_FINDER.reqTagName() );
		this.finderSupplier = finderSupplier; 
	}
    
    //****************************************************************
    //
    //****************************************************************

    public GetByFinderReq< UDM, FINDER_MODEL > newModel() {
		return new GetByFinderReq< UDM, FINDER_MODEL >( null, 0, null );
	}
	
    @Override
    public GetByFinderReq< UDM, FINDER_MODEL > toModel( SnPoint src, GetByFinderReq< UDM , FINDER_MODEL> target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asInt.get( ATR.blockSize, target.blockSize );
    	
    	src.children.snBottom.get( finderNode -> {
    		this.finderSupplier.createFinder( finderNode, finderModel -> {
    			target.finder.set( (FINDER_MODEL) finderModel );
    		});
    	});
        
        return target;
    }
    
    @Override
    public SnPoint toNode( GetByFinderReq< UDM, FINDER_MODEL > src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asInt.set( ATR.blockSize, src.blockSize );
    	
    	src.finder.get( filter -> filter.createTransformer().toNode( filter, target.children :: add ) );

    	
        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
