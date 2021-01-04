package com.xelara.aladdin.magiclamp.model;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.parser.Parser;
import com.xelara.structure.snode.SN;
import com.xelara.structure.snode.SNode;
import com.xelara.structure.xml.XML;

/**
 *
 * @author Macit Kandemir
 */
public class WishModelParser extends DataModelParser < WishModel > {

    
    public enum ATR { invokeID, cmd, sbj, userID };
    
    
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
    
        Parser.STR.parse( ATR.invokeID	,src ,target.invokeID );
        Parser.STR.parse( ATR.cmd		,src ,target.cmd );
        Parser.STR.parse( ATR.sbj		,src ,target.sbj );
        Parser.STR.parse( ATR.userID	,src ,target.userID );
        
        SNode objectNode = src.getFirstChild();
        if( objectNode != null) {
        	XML.parse(objectNode, target.object :: setValue );
        }
        
        return target;
    }
    
    @Override
    public SNode parse( WishModel src, SNode target ) {
        
        Parser.STR.parse( ATR.invokeID 	,src.invokeID	,target );
        Parser.STR.parse( ATR.cmd 		,src.cmd 		,target );
        Parser.STR.parse( ATR.sbj 		,src.sbj 		,target );
        Parser.STR.parse( ATR.userID 	,src.userID		,target );

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
