package com.xelara.aladdin.magiclamp.model;

import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;
import com.xelara.structure.xml.XML;

/**
 *
 * @author Macit Kandemir
 */
public class WishModelParser extends UnitModelParser < WishModel > {

    
    public enum ATR { section, cmd, sbj, userID };
    
    
    //****************************************************************
    //
    //****************************************************************

    public WishModelParser() {
        super( "Wish" );
    }

    //****************************************************************
    //
    //****************************************************************

    @Override
    public WishModel newModel() {
        return new WishModel();
    }
    
    //****************************************************************
    //
    //****************************************************************

    @Override
    public WishModel parse( SNode src, WishModel target ) {
    
        Parser.STR.parse( src, ATR.section 	,target.section );
        Parser.STR.parse( src, ATR.cmd 		,target.cmd );
        Parser.STR.parse( src, ATR.sbj 		,target.sbj );
        Parser.STR.parse( src, ATR.userID 	,target.userID );
        
        SNode objectNode = src.getFirstChild();
        if( objectNode != null) {
        	XML.parse(objectNode, target.object :: setValue );
        }
        
        return target;
    }
    
    @Override
    public SNode parse( WishModel src, SNode target ) {
        
        Parser.STR.parse( src.section 	, ATR.section 	,target );
        Parser.STR.parse( src.cmd 		, ATR.cmd 			,target );
        Parser.STR.parse( src.sbj 		, ATR.sbj 			,target );
        Parser.STR.parse( src.userID 		, ATR.userID		,target );

        target.setValueType( SN.VALUE_TYPE_SL_VOID );

        src.object.getValue( objectString -> {
        	XML.parse(objectString, objectNode -> {
        		target.addChild(objectNode);
                target.setValueType( SN.VALUE_TYPE_CHILDREN );
        	});
        });
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
