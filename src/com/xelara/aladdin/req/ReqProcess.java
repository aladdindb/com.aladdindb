package com.xelara.aladdin.req;

import java.util.function.Consumer;

import com.xelara.aladdin.UnitsChannel;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

public abstract class ReqProcess <

	RESP 		extends DataModel	< RESP >,
	UDM 		extends DataModel 	< UDM >, 
	DATA_MODEL 	extends ReqProcess 	< RESP, UDM, DATA_MODEL >
 
> extends DataParser< DATA_MODEL > implements DataModel< DATA_MODEL >, Runnable { 


    //****************************************************************
    //						Class Attributes
    //****************************************************************
	
	public final Var < Consumer < RESP > > 	respConsumer = new Var<>();
	
	private final DataParser	< RESP >	respParser;
	private final UnitsChannel	< UDM > 	unitsChanel;
	
	
    //****************************************************************
    //						Constructors
    //****************************************************************
	
	public ReqProcess( String nodeKey, DataParser< RESP > respParser, UnitsChannel< UDM > unitsChanel ) {
		
		super( nodeKey );
		
		this.respParser 	= respParser;
		this.unitsChanel 	= unitsChanel;
		
		this.unitGroupID.set( this.unitsChanel.unitGroupID );
	}

    //****************************************************************
    //   				ReqProcess Implements
    //****************************************************************
    
	@Override
	public void run() {
		toNode( (DATA_MODEL) this, reqNode -> {
			unitsChanel.channel.sendReq( reqNode, respNode -> {
				this.respConsumer.get( respConsumer -> { 
					this.respParser.toModel( respNode, respConsumer );
				});
			});
		});
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	public final Var< String > unitGroupID = new Var<>();
	
	@Override
	public void fill( DATA_MODEL  model ) {
		this.unitGroupID.set( model.unitGroupID );
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

    private enum ATR { unitGroupID };
	
    @Override
    public DATA_MODEL toModel( SnPoint src, DATA_MODEL target ) {
    
    	var srcAtr = new SnAttributeAccess( src );

    	srcAtr.asStr.get( ATR.unitGroupID ,target.unitGroupID );
        
        return target;
    }
    
    @Override
    public SnPoint toNode( DATA_MODEL src, SnPoint target ) {
        
    	var targetAtr = new SnAttributeAccess( target );

    	targetAtr.asStr.set( ATR.unitGroupID ,src.unitGroupID );

        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
}
