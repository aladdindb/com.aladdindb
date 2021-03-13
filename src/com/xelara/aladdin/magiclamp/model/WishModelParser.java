package com.xelara.aladdin.magiclamp.model;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;
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
    public WishModel toModel( SnPoint snPoint, WishModel model ) {
    
    	var parse = new SnAttributeAccess( snPoint );
    	
        parse.asStr.get( ATR.invokeID		, model.invokeID 	);
        parse.asStr.get( ATR.cmd	 		, model.cmd 		);
        parse.asStr.get( ATR.sbj			, model.sbj 		);
        parse.asStr.get( ATR.userID		, model.userID 		);
        
        snPoint.children.snBottom.get( objectNode -> {
        	XML.parse( objectNode, model.object :: set );
        });
        
        return model;
    }
    
    @Override
    public SnPoint toNode( WishModel model, SnPoint snPoint ) {
        
    	var parse = new SnAttributeAccess( snPoint );

    	parse.asStr.set( ATR.invokeID 		,model.invokeID	);
    	parse.asStr.set( ATR.cmd 			,model.cmd 		);
    	parse.asStr.set( ATR.sbj 			,model.sbj 		);
    	parse.asStr.set( ATR.userID 		,model.userID	);

//        snPoint.valueType.set( SnValueType.SINGLE_LINE );

        model.object.get( objectString -> {
        	XML.parse( objectString, snObjectPoint -> {
        		snPoint.children.add( snObjectPoint );
//                node.valueType.set( SnValueType.CHILDREN );
        	});
        });
        
        return snPoint;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
