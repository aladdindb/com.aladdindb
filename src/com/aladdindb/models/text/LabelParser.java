package com.aladdindb.models.text;

import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;

/**
 *
 * @author Macit Kandemir
 */
public class LabelParser extends Transformer < LabelModel > {
    
    
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
    public LabelModel newStore() {
        return new LabelModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public LabelModel toStore( SnPoint src, LabelModel target ) {
        
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
