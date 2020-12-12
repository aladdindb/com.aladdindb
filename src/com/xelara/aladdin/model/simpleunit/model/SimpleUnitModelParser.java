package com.xelara.aladdin.model.simpleunit.model;

import com.xelara.aladdin.unit.model.DbUnitModelParser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */

public class SimpleUnitModelParser  extends DbUnitModelParser < SimpleUnitModel > {
    
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SimpleUnitModel newModel() {
        return new SimpleUnitModel();
    }
    
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public SimpleUnitModel parse( SNode src, SimpleUnitModel target ) {
    	super.parse( src, target );

    	return target;
    }
    
    @Override
    public SNode parse( SimpleUnitModel src, SNode target ) {
    	super.parse( src, target );
    	
    	target.setValueType( SN.VALUE_TYPE_SL_VOID );
    	return target;
    }

}
