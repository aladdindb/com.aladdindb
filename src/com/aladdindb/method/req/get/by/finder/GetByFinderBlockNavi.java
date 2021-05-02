package com.aladdindb.method.req.get.by.finder;

import com.aladdindb.MagicLamp;
import com.aladdindb.finder.Finder;
import com.aladdindb.method.req.get.block.BlockReqProcess;
import com.aladdindb.method.req.get.block.BlockNavi;
import com.aladdindb.method.resp.get.block.BlockResp;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.LineNavigator;

/**
*
* @author Macit Kandemir
*/
public class GetByFinderBlockNavi < UDM extends DataModel 	< UDM > > 
	extends BlockNavi < UDM, GetByFinderReq < UDM ,  ? extends Finder< UDM, ? > > >   { 
	
    public GetByFinderBlockNavi( MagicLamp<UDM> unitsChannel, int blockSize, Finder< UDM, ? extends Finder< UDM, ? > > finder ) {
    	super( 	unitsChannel, blockSize, new GetByFinderReqProcess( blockSize, finder, unitsChannel ) );
    }

}
