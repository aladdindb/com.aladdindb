package com.aladdindb.defaultmodels.text;

import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;

/**
 *
 * @author Macit Kandemir
 */
public class LabelParser extends DataParser < LabelModel > {
    
    
	public LabelParser() {
		super("label");
	}	
	
    public LabelParser( String key ) {
        super( key );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LabelModel newModel() {
        return new LabelModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LabelModel toModel( SnPoint src, LabelModel target ) {
        
    	src.value.get( target :: set );
        
        return target;
    }
    
    
    @Override
    public SnPoint toNode( LabelModel src, SnPoint target ) {
        
        src.get( target.value :: set );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
