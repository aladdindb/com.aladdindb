package com.xelara.aladdin.model.list.input;

import com.xelara.aladdin.unit.model.DbUnitListModelParser;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 * @param <LIST>
 */
public class InputListModelParser  extends DbUnitListModelParser <

	InputModel, InputListModel, InputModelParser
	
> {

    //****************************************************************
    //
    //****************************************************************
    
    public InputListModelParser( String listKey ) {
        super( listKey, new InputModelParser() );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public InputListModel newModel() {
        return  new InputListModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public InputListModel  parse( SNode src, InputListModel target ) {
    	
        this.parseList( src, target );
        
        return target;
    }
    
    @Override
    public SNode parse( InputListModel src, SNode target ) {
    	
        this.parseList( src, target );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
