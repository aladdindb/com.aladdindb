package com.xelara.aladdin.index.model;

import com.xelara.aladdin.unit.model.DbUnitListModelParser;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class IndexListModelParser  extends DbUnitListModelParser < IndexModel, IndexListModel, IndexModelParser > {
    

    //****************************************************************
    //
    //****************************************************************

    public IndexListModelParser() {
        super( "Index", new IndexModelParser() );
    }

    //****************************************************************
    //
    //****************************************************************

    @Override
    public IndexListModel newModel() {
        return new IndexListModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public IndexListModel parse( SNode src, IndexListModel target ) {
        
        this.parseList( src, target );
        
        return target;
    }
    
    @Override
    public SNode parse( IndexListModel src, SNode target ) {
        
        this.parseList( src, target );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
