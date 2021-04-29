package com.aladdindb.method.req.get.all;

import com.aladdindb.UnitsChannel;
import com.aladdindb.method.req.get.block.GetBlockNavi;
import com.aladdindb.structure.DataModel;

/**
*
* @author Macit Kandemir
*/
public class GetAllBlockNavi < UDM extends DataModel < UDM > > extends GetBlockNavi < UDM, GetAllReqModel  >   { 
	
    public GetAllBlockNavi( UnitsChannel < UDM > unitsChannel, int blockSize ) {
    	super( 	unitsChannel, blockSize, new GetAllReqProcess< UDM >( blockSize,  unitsChannel ) );
    }
	
	
}
