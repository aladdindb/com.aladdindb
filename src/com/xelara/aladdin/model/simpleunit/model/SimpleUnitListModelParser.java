package com.xelara.aladdin.model.simpleunit.model;

import com.xelara.aladdin.unit.model.DbUnitListModelParser;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class SimpleUnitListModelParser  extends DbUnitListModelParser < SimpleUnitModel, SimpleUnitListModel,  SimpleUnitModelParser > {
    
    //****************************************************************
    //
    //****************************************************************

    public SimpleUnitListModelParser() {
        super( "Units", new SimpleUnitModelParser() );// "Unit" ) );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SimpleUnitListModel newModel() {
        return new SimpleUnitListModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SimpleUnitListModel parse( SNode src, SimpleUnitListModel target ) {
        
        this.parseList( src, target );
        
        return target;
    }
    
    @Override
    public SNode parse( SimpleUnitListModel src, SNode target ) {
        
        this.parseList( src, target );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
