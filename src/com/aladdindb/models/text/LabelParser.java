package com.aladdindb.models.text;

import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;

/**
 *
 * @author Macit Kandemir
 */
public class LabelParser extends Transformer < UnitLabel > {
    
    
	public LabelParser() {
		super("UnitLabel");
	}	
	
    public LabelParser( String key ) {
        super( key );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public UnitLabel newModel() {
        return new UnitLabel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public UnitLabel toModel( SnPoint src, UnitLabel target ) {
        
    	src.value.get( target :: set );
        
        return target;
    }
    
    
    @Override
    public SnPoint toNode( UnitLabel src, SnPoint target ) {
        
        src.get( target.value :: set );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
