package com.aladdindb.method.req.search;

import com.aladdindb.Type;
import com.aladdindb.finder.Finder;
import com.aladdindb.finder.FinderSupport;
import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.sorter.SorterSupport;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class SearchReqTransformer <

	UDM 			extends DataModel		< UDM >,
	FINDER_MODEL 	extends Finder		< UDM, FINDER_MODEL>,
	SORTER_MODEL 	extends Sorter		< UDM, SORTER_MODEL >

>  extends ReqTransformer< SearchReq < UDM, FINDER_MODEL, SORTER_MODEL > >  {
	

    private enum ATR { blockSize };
    
    private final FinderSupport< UDM > finderSupport; 
    private final SorterSupport< UDM > sorterSupport; 
    
	public SearchReqTransformer( FinderSupport< UDM > finderSupport, SorterSupport< UDM > sorterSupport ) {
		super( Method.SEARCH.reqTagName() );
		
		this.finderSupport = finderSupport; 
		this.sorterSupport = sorterSupport;
	}
    
    //****************************************************************
    //
    //****************************************************************

    public SearchReq< UDM, FINDER_MODEL, SORTER_MODEL > newModel() {
		return new SearchReq<>();
	}
	
    @Override
    public SearchReq< UDM, FINDER_MODEL, SORTER_MODEL > toModel( SnPoint src, SearchReq< UDM , FINDER_MODEL, SORTER_MODEL> target ) {
    	super.toModel(src, target);
    	
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asInt.get( ATR.blockSize, target.blockSize );
    	
    	src.children.forEach( node -> {
    		node.key.get( key -> {
    			var type = key.split(":");
    			switch( type[0] ) {
    				case "finder": 
    		    		this.finderSupport.newFinder( node, finderModel -> {
    		    			target.finder.set( (FINDER_MODEL) finderModel );
    		    		});break;
    				case "sorter": 
    		    		this.sorterSupport.newSorter( node, sorterModel -> {
    		    			target.sorter.set( (SORTER_MODEL) sorterModel );
    		    		});break;
    			}
    		});
    	});
    	
//    	src.children.snBottom.get( finderNode -> {
//    		this.finderSupport.newFinder( finderNode, finderModel -> {
//    			target.finder.set( (FINDER_MODEL) finderModel );
//    		});
//    	});
        
        return target;
    }
    
    @Override
    public SnPoint toNode( SearchReq< UDM, FINDER_MODEL, SORTER_MODEL > src, SnPoint target ) {
        super.toNode(src, target);
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asInt.set( ATR.blockSize, src.blockSize );
    	
    	src.finder.get( finder -> finder.newTransformer().toNode( finder, target.children :: add ) );
    	src.sorter.get( sorter -> sorter.newTransformer().toNode( sorter, target.children :: add ) );

    	
        return target;
    }
	
    //****************************************************************
    //
    //****************************************************************
    
}
