package com.aladdindb.req.add;

import com.aladdindb.UnitsChannel;
import com.aladdindb.req.ReqProcess;
import com.aladdindb.resp.add.AddRespModel;
import com.aladdindb.resp.add.AddRespParser;
import com.aladdindb.structure.DataModel;


public class AddReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < AddReqModel< UDM >, AddRespModel, UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public AddReqProcess( UDM unitData, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new AddReqModel < UDM > ( unitsChanel.unitGroupID, unitData );
		
		this.req		.set ( req );
		this.reqParser	.set ( new AddReqParser	< UDM > ( unitsChanel.unitDataParser ) );
		this.respParser	.set ( new AddRespParser() );
	}

	

}
