package com.aladdindb.method.req.search;

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
public final class SearchReqTransformer <

	UDM 			extends DataModel	< UDM >,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL>

>  extends ReqTransformer< SearchReq < UDM, FINDER_MODEL > >  {
	

    private enum ATR { blockSize };
    
    private final FinderSupplier< UDM > finderSupplier; 
    
	public SearchReqTransformer( FinderSupplier< UDM > finderSupplier ) {
		super( Method.SEARCH.reqTagName() );
		this.finderSupplier = finderSupplier; 
	}
    
    //****************************************************************
    //
    //****************************************************************

    public SearchReq< UDM, FINDER_MODEL > newModel() {
		return new SearchReq< UDM, FINDER_MODEL >( null, 0, null );
	}
	
    @Override
    public SearchReq< UDM, FINDER_MODEL > toModel( SnPoint src, SearchReq< UDM , FINDER_MODEL> target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asInt.get( ATR.blockSize, target.blockSize );
    	
    	src.children.snBottom.get( finderNode -> {
    		this.finderSupplier.newFinder( finderNode, finderModel -> {
    			target.finder.set( (FINDER_MODEL) finderModel );
    		});
    	});
        
        return target;
    }
    
    @Override
    public SnPoint toNode( SearchReq< UDM, FINDER_MODEL > src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asInt.set( ATR.blockSize, src.blockSize );
    	
    	src.finder.get( filter -> filter.newTransformer().toNode( filter, target.children :: add ) );

    	
        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
