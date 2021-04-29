package com.aladdindb.method.req;

import java.util.function.Consumer;

import com.aladdindb.UnitsChannel;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataParser;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.types.SnAttributeAccess;
import com.aladdindb.util.Var;

public abstract class ReqProcess <

	REQ_MODEL 	extends ReqModel	< REQ_MODEL >,
	RESP 		extends DataModel	< RESP >,
	UDM 		extends DataModel 	< UDM > 
 
> implements Runnable { 


    //****************************************************************
    //						Class Attributes
    //****************************************************************
		
	public final Var < REQ_MODEL 					> req 			= new Var<>(); 
	
	public final Var < UnitsChannel	< UDM 		> 	> unitsChanel 	= new Var<>();
	public final Var < DataParser 	< REQ_MODEL > 	> reqParser 	= new Var<>();
	public final Var < DataParser 	< RESP 		> 	> respParser 	= new Var<>();
	public final Var < Consumer 	< RESP 		> 	> respConsumer 	= new Var<>();
	
	
    //****************************************************************
    //   						Process
    //****************************************************************
    
	@Override
	public void run() {
		this.unitsChanel.get( unitsChanel -> {
			this.reqParser.get( reqParser -> {
				reqParser.toNode( req, reqNode -> {
					unitsChanel.channel.sendReq( reqNode, respNode -> {
						this.respConsumer.get( respConsumer -> {
							this.respParser.get( respParser -> {
								respParser.toModel( respNode, respConsumer );
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
