module com.xelara.aladdin {
	
	exports com.xelara.aladdin;
	
    exports com.xelara.aladdin.req.add;
    exports com.xelara.aladdin.req.get.by.id;
    exports com.xelara.aladdin.req.get.all;
    exports com.xelara.aladdin.req.update;
    exports com.xelara.aladdin.req.get.by.filter;

    exports com.xelara.aladdin.resp.add;
    exports com.xelara.aladdin.resp.get.by.id;
    exports com.xelara.aladdin.resp.get;
    exports com.xelara.aladdin.resp.get.block;
    exports com.xelara.aladdin.resp.update; 
    exports com.xelara.aladdin.resp.remove; 

    exports com.xelara.aladdin.core;
    exports com.xelara.aladdin.core.units;
    exports com.xelara.aladdin.core.units.models;
    
    exports com.xelara.aladdin.core.filter;
    exports com.xelara.aladdin.core.filter.types;
    exports com.xelara.aladdin.core.filter.logical;

    exports com.xelara.aladdin.core.models.login;        
    exports com.xelara.aladdin.core.models.server;        
    exports com.xelara.aladdin.core.models.text;        
    
	requires transitive com.xelara.structure;
	
}