package com.xelara.aladdin.magiclamp.model;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.node.Snode;
import com.xelara.structure.attributes.AParser;
import com.xelara.structure.node.SnValueType;
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
        super( "wish" );
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
    public WishModel fromNode( Snode node, WishModel model ) {
    
    	var parse = new AParser( node );
    	
        parse.strPrs.get( ATR.invokeID		, model.invokeID 	);
        parse.strPrs.get( ATR.cmd	 		, model.cmd 		);
        parse.strPrs.get( ATR.sbj			, model.sbj 		);
        parse.strPrs.get( ATR.userID			, model.userID 		);
        
        Snode objectNode = node.childs.getFirst();
        
        if( objectNode != null) {
        	XML.parse(objectNode, model.object :: setValue );
        }
        
        return model;
    }
    
    @Override
    public Snode toNode( WishModel model, Snode node ) {
        
    	var parse = new AParser( node );

    	parse.strPrs.set( ATR.invokeID 	,model.invokeID	);
    	parse.strPrs.set( ATR.cmd 			,model.cmd 		);
    	parse.strPrs.set( ATR.sbj 			,model.sbj 		);
    	parse.strPrs.set( ATR.userID 		,model.userID	);

        node.setValueType( SnValueType.SL_VOID );

        model.object.getValue( objectString -> {
        	XML.parse( objectString, objectNode -> {
        		node.childs.add( objectNode );
                node.setValueType( SnValueType.CHILDREN );
        	});
        });
        
        return node;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
