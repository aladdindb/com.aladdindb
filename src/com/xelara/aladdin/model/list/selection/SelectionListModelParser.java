package com.xelara.aladdin.model.list.selection;

import com.xelara.aladdin.unit.model.DbUnitListModelParser;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 * @param <LIST>
 */
public class SelectionListModelParser  extends DbUnitListModelParser <

	SelectionModel, SelectionListModel, SelectionModelParser
	
> {

    //****************************************************************
    //
    //****************************************************************
    
    public SelectionListModelParser( String listKey ) {
        super( listKey, new SelectionModelParser() );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SelectionListModel newModel() {
        return  new SelectionListModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SelectionListModel  parse( SNode src, SelectionListModel target ) {
    	
        this.parseList( src, target );
        
        return target;
    }
    
    @Override
    public SNode parse( SelectionListModel src, SNode target ) {
    	
        this.parseList( src, target );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
