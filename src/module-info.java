module com.xelara.aladdin {
	
    exports com.xelara.aladdin.client;
    exports com.xelara.aladdin.client.req.add;
    exports com.xelara.aladdin.client.req.get.byid;
    exports com.xelara.aladdin.client.req.update;

    exports com.xelara.aladdin.server;
    
    exports com.xelara.aladdin.server.resp.add;
    exports com.xelara.aladdin.server.resp.getbyid;
    exports com.xelara.aladdin.server.resp.update; 
    exports com.xelara.aladdin.server.resp.remove; 

    exports com.xelara.aladdin.core;
    exports com.xelara.aladdin.core.units;
    exports com.xelara.aladdin.core.units.models;
    
    exports com.xelara.aladdin.core.filter;

    exports com.xelara.aladdin.core.models.login;        
    exports com.xelara.aladdin.core.models.server;        
    exports com.xelara.aladdin.core.models.text;        
    
	requires transitive com.xelara.structure;
	
}