package com.xelara.aladdin;

import java.util.HashMap;
import java.util.function.Consumer;

import com.xelara.structure.DataModel;

public class MagicLamp extends HashMap < String , Genie < ? extends DataModel< ? > > > {

//    public void get( WishModel wish, Consumer< Genie< ? extends DataModel< ? > > > genieConsumer ) {
//    	wish.invokeID.get( invokeID ->  {
//        	var genie = get(invokeID);
//        	if( genie != null ) genieConsumer.accept( genie );  
//    	});
//    }

    public void getGenie( String unitGroupID, Consumer< Genie< ? extends DataModel< ? > > > consumer ) {
    	var genie = get(unitGroupID);
    	if( genie != null ) consumer.accept( genie );  
    }
}
