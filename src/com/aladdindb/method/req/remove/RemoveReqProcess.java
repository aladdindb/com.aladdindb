package com.aladdindb.method.req.remove;

import com.aladdindb.UnitsChannel;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.remove.RemoveResp;
import com.aladdindb.method.resp.remove.RemoveRespParser;
import com.aladdindb.structure.DataModel;


public class RemoveReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < RemoveReqModel, RemoveResp< UDM > , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public RemoveReqProcess( String unitID, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new RemoveReqModel( unitsChanel.unitGroupID, unitID );
		
		this.req		.set ( req );
		this.reqParser	.set ( new RemoveReqParser());
		this.respParser	.set ( new RemoveRespParser<UDM>( unitsChanel.unitDataParser ) );
	}

	

}
