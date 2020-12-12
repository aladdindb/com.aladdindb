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
    
        Parser.STR	.parse( src, ATR.id     	,target.id      );
        Parser.STR	.parse( src, ATR.label  	,target.label   );
        Parser.STR	.parse( src, ATR.tsNew  	,target.tsNew   );
        Parser.STR	.parse( src, ATR.tsUpdate  	,target.tsUpdate   );
        
        return target;
    }
    
    @Override
    public SNode parse( DUM src, SNode target ) {
        
        Parser.STR	.parse( src.id      	, ATR.id    	,target );
        Parser.STR	.parse( src.label   	, ATR.label 	,target );
        Parser.STR	.parse( src.tsNew   	, ATR.tsNew 	,target );
        Parser.STR	.parse( src.tsUpdate   	, ATR.tsUpdate 	,target );

        target.setValueType( SN.VALUE_TYPE_CHILDREN );
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
    
    
}
