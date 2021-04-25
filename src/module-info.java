module com.xelara.aladdin {
	
	exports com.xelara.aladdin;
	
    exports com.xelara.aladdin.req.add;
    exports com.xelara.aladdin.req.get.byid;
    exports com.xelara.aladdin.req.get.all;
    exports com.xelara.aladdin.req.update;
    exports com.xelara.aladdin.req.get.filtered;

    exports com.xelara.aladdin.resp.add;
    exports com.xelara.aladdin.resp.get.byid;
    exports com.xelara.aladdin.resp.get.all;
    exports com.xelara.aladdin.resp.get.all.block;
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