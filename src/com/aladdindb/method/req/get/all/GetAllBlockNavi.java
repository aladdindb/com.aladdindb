package com.aladdindb.method.req.get.all;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.req.get.block.BlockNavi;
import com.aladdindb.structure.DataModel;

/**
*
* @author Macit Kandemir
*/
public class GetAllBlockNavi < UDM extends DataModel < UDM > > extends BlockNavi < UDM, GetAllReq  >   { 
	
    public GetAllBlockNavi( MagicLamp < UDM > unitsChannel, int blockSize ) {
    	super( 	unitsChannel, blockSize, new GetAllReqProcess< UDM >( blockSize,  unitsChannel ) );
    }
	
	
}
