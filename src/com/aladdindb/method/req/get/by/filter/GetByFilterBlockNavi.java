package com.aladdindb.method.req.get.by.filter;

import com.aladdindb.UnitsChannel;
import com.aladdindb.filter.Filter;
import com.aladdindb.method.req.get.block.GetAllBlockReqProcess;
import com.aladdindb.method.req.get.block.GetBlockNavi;
import com.aladdindb.method.resp.get.block.GetBlockRespModel;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class GetByFilterBlockNavi < UDM extends DataModel 	< UDM > > 
	extends GetBlockNavi < UDM, GetByFilterReqModel < UDM ,  ? extends Filter< UDM, ? > > >   { 
	
    public GetByFilterBlockNavi( UnitsChannel<UDM> unitsChannel, int blockSize, Filter< UDM, ? extends Filter< UDM, ? > > filter ) {
    	super( 	unitsChannel, blockSize, new GetByFilterReqProcess( blockSize, filter, unitsChannel ) );
    }

    private static < UDM extends DataModel 	< UDM > > GetByFilterReqProcess< UDM, ? extends Filter< UDM, ? > > createReqProcess() {
    	return null;
    }
}
