package com.xelara.aladdin.unit.model;

import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public abstract class DbUnitModelParser < DUM extends DbUnitModel < DUM > > extends UnitModelParser < DUM > {
	

    public enum ATR     { id, label, tsNew, tsUpdate };
	
	public DbUnitModelParser() {
		super("Unit");
	}
    
    public DbUnitModelParser( String key ) {
        super( key );
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public DUM  parse( SNode src, DUM  target ) {
    
        Parser.STR	.parse( ATR.id, src     	,target.id      );
        Parser.STR	.parse( ATR.label, src  	,target.label   );
        Parser.STR	.parse( ATR.tsNew, src  	,target.tsNew   );
        Parser.STR	.parse( ATR.tsUpdate, src  	,target.tsUpdate   );
        
        return target;
    }
    
    @Override
    public SNode parse( DUM src, SNode target ) {
        
        Parser.STR	.parse( ATR.id      	, src.id    	,target );
        Parser.STR	.parse( ATR.label   	, src.label 	,target );
        Parser.STR	.parse( ATR.tsNew   	, src.tsNew 	,target );
        Parser.STR	.parse( ATR.tsUpdate   	, src.tsUpdate 	,target );

        target.setValueType( SN.VALUE_TYPE_CHILDREN );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
