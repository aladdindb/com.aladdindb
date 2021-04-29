module com.aladdindb {
	
	exports com.aladdindb;
	
    exports com.aladdindb.method.req.add;
    exports com.aladdindb.method.req.get.by.id;
    exports com.aladdindb.method.req.get.all;
    exports com.aladdindb.method.req.update;
    exports com.aladdindb.method.req.get.by.filter;

    exports com.aladdindb.method.resp.add;
    exports com.aladdindb.method.resp.get.by.id;
    exports com.aladdindb.method.resp.get;
    exports com.aladdindb.method.resp.get.block;
    exports com.aladdindb.method.resp.update; 
    exports com.aladdindb.method.resp.remove; 

    exports com.aladdindb.units;
    exports com.aladdindb.units.models;
    
    exports com.aladdindb.filter;
    exports com.aladdindb.filter.types;
    exports com.aladdindb.filter.logical;

    exports com.aladdindb.defaultmodels.login;        
    exports com.aladdindb.defaultmodels.server;        
    exports com.aladdindb.defaultmodels.text;        
    

    
    exports com.aladdindb.structure;
    exports com.aladdindb.structure.types;
    
    exports com.aladdindb.structure.sn;
    exports com.aladdindb.structure.sn.props;
    
    exports com.aladdindb.structure.sa;
    exports com.aladdindb.structure.sa.props;
    
    exports com.aladdindb.structure.xml;
    
    
    exports com.aladdindb.util.time;
    exports com.aladdindb.util;
    
    
}