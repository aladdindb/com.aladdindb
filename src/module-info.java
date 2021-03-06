module com.aladdindb {
	
	exports com.aladdindb;
	exports com.aladdindb.method.req;
    exports com.aladdindb.method.req.add;
    exports com.aladdindb.method.req.get.by.id;
    exports com.aladdindb.method.req.get.all;
    exports com.aladdindb.method.req.update;
    exports com.aladdindb.method.req.search;
    exports com.aladdindb.method.req.get.block;
    exports com.aladdindb.method.req.close.method.session;

    exports com.aladdindb.method.resp.add;
    exports com.aladdindb.method.resp.get.by.id;
    exports com.aladdindb.method.resp.get;
    exports com.aladdindb.method.resp.get.block;
    exports com.aladdindb.method.resp.search; 
    exports com.aladdindb.method.resp.update; 
    exports com.aladdindb.method.resp.remove; 
    exports com.aladdindb.method.resp.close.method.session; 

    exports com.aladdindb.store;
    exports com.aladdindb.store.models;
    
    exports com.aladdindb.finder;
    exports com.aladdindb.finder.types;
    exports com.aladdindb.finder.logical;

    exports com.aladdindb.sorter;
    exports com.aladdindb.sorter.types;
    
    exports com.aladdindb.models.login;        
    exports com.aladdindb.models.server;        
    exports com.aladdindb.models.text;        
    

    
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