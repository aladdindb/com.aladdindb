package com.xelara.aladdin.magiclamp.model;

import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnValueType;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.AParser;
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
    public WishModel fromNode( SnPoint snPoint, WishModel model ) {
    
    	var parse = new AParser( snPoint );
    	
        parse.strPrs.get( ATR.invokeID		, model.invokeID 	);
        parse.strPrs.get( ATR.cmd	 		, model.cmd 		);
        parse.strPrs.get( ATR.sbj			, model.sbj 		);
        parse.strPrs.get( ATR.userID		, model.userID 		);
        
        snPoint.deepLine.start.get( objectNode -> {
        	XML.parse( objectNode, model.object :: setValue );
        });
        
        return model;
    }
    
    @Override
    public SnPoint toNode( WishModel model, SnPoint snPoint ) {
        
    	var parse = new AParser( snPoint );

    	parse.strPrs.set( ATR.invokeID 		,model.invokeID	);
    	parse.strPrs.set( ATR.cmd 			,model.cmd 		);
    	parse.strPrs.set( ATR.sbj 			,model.sbj 		);
    	parse.strPrs.set( ATR.userID 		,model.userID	);

//        snPoint.valueType.set( SnValueType.SINGLE_LINE );

        model.object.getValue( objectString -> {
        	XML.parse( objectString, snObjectPoint -> {
        		snPoint.deepLine.add( snObjectPoint );
//                node.valueType.set( SnValueType.CHILDREN );
        	});
        });
        
        return snPoint;
    }
    
    //****************************************************************
    //
    //****************************************************************

}
