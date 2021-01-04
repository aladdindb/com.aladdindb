package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class LabelModelParser extends DataModelParser < LabelModel > {
    
    
	public LabelModelParser() {
		super("label");
	}	
	
    public LabelModelParser( Enum<?> key ) {
        this( key.name());
    }
    
    public LabelModelParser( String key ) {
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
    public LabelModel parse( SNode src, LabelModel target ) {
        
        target.setValue( src.getValue() );
        
        return target;
    }
    
    
    @Override
    public SNode parse( LabelModel src, SNode target ) {
        
        src.getValue( target :: setValue );
        
        target.setValueType( SN.VALUE_TYPE_SL_CLOSE );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
