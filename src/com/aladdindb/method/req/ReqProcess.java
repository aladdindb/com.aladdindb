package com.aladdindb.method.req;

import java.util.function.Consumer;

import com.aladdindb.MagicLamp;
import com.aladdindb.structure.Store;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;
import com.aladdindb.util.Var;

public abstract class ReqProcess <

	REQ_MODEL 	extends Req			< REQ_MODEL >,
	RESP 		extends Store	< RESP >,
	UDM 		extends Store 	< UDM > 
 
> implements Runnable { 


    //****************************************************************
    //						Class Attributes
    //****************************************************************
		
	public final Var < REQ_MODEL 					> req 					= new Var<>(); 
	
	public final Var < MagicLamp		< UDM 		> 	> magicLamp 		= new Var<>();
	public final Var < Transformer 	< REQ_MODEL > 	> reqTransformer 	= new Var<>();
	public final Var < Transformer 	< RESP 		> 	> respTransformer 	= new Var<>();
	public final Var < Consumer 		< RESP 		> 	> respConsumer 		= new Var<>();
	
	
    //****************************************************************
    //   						Process
    //****************************************************************
    
	@Override
	public void run() {
		this.magicLamp.get( magicLamp -> {
			this.reqTransformer.get( reqTransformer -> {
				reqTransformer.toNode( req, reqNode -> {
					magicLamp.genieConnection.sendReq( reqNode, respNode -> {
						this.respConsumer.get( respConsumer -> {
							this.respTransformer.get( respTransformer -> {
								respTransformer.toStore( respNode, respConsumer );
							});
						});
					});
				});
			});
		});
		
	}
	
    //****************************************************************
    //   				
    //****************************************************************
	
}
