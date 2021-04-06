package com.xelara.aladdin.magiclamp.wishes.addunit;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.sn.props.SnValueType;
import com.xelara.structure.types.SnAttributeAccess;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddModelParser < UGM extends DataModel< UGM > > extends DataModelParser < AddModel< UGM > > {
	

    private enum ATR { userID, unitGroupID };
    
	
    private final DataModelParser< UGM > cmdDataParser;
    
    
    //****************************************************************
    //
    //****************************************************************

    public AddModelParser( DataModelParser< UGM > cmdDataParser ) {
		super("wish:addUnit");
		this.cmdDataParser = cmdDataParser; 
	}
    
    //****************************************************************
    //
    //****************************************************************

    public AddModel< UGM > newModel() {
		return new AddModel< UGM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    @Override
    public AddModel< UGM >  toModel( SnPoint src, AddModel< UGM >  target ) {
    
    	var parse = new SnAttributeAccess( src );

    	parse.asStr	.get( ATR.userID		, target.userID     	);
    	parse.asStr	.get( ATR.unitGroupID	, target.unitGroupID );
        
        this.cmdDataParser.toModelFromParent( src	, target.data );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddModel< UGM > src, SnPoint target ) {
        
    	var parse = new SnAttributeAccess( target );

    	parse.asStr	.set( ATR.userID      	, src.userID 		); 
    	parse.asStr	.set( ATR.unitGroupID   , src.unitGroupID	);
        
        this.cmdDataParser.toParentNode( src.data, target );

        return target;
    }
    
    
    
}
