package com.aladdindb.method.req.get.by.id;

import com.aladdindb.UnitsChannel;
import com.aladdindb.method.req.ReqProcess;
import com.aladdindb.method.resp.get.by.id.GetByIdRespModel;
import com.aladdindb.method.resp.get.by.id.GetByIdRespParser;
import com.aladdindb.structure.DataModel;


public class GetByIdReqProcess < UDM extends DataModel< UDM > > extends ReqProcess < GetByIdReqModel, GetByIdRespModel < UDM > , UDM > {

	
    //****************************************************************
    //						Constructors
    //****************************************************************

	public GetByIdReqProcess( String unitID, UnitsChannel< UDM > unitsChanel ) {
		
		this.unitsChanel.set ( unitsChanel);

		var req = new GetByIdReqModel( unitsChanel.unitGroupID, unitID );
		
		this.req		.set ( req );
		this.reqParser	.set ( new GetByIdReqParser());
		this.respParser	.set ( new GetByIdRespParser < UDM > ( unitsChanel.unitDataParser ) );
	}

	

}
