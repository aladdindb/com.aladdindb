module com.xelara.aladdin {
	
    exports com.xelara.aladdin;
    
    exports com.xelara.aladdin.magiclamp;
    exports com.xelara.aladdin.magiclamp.model;
    
    exports com.xelara.aladdin.genie;
    
    exports com.xelara.aladdin.unit;
    exports com.xelara.aladdin.unit.model;
    
    exports com.xelara.aladdin.model.list.selection;
    exports com.xelara.aladdin.model.list.input;

    exports com.xelara.aladdin.model.login;        
    exports com.xelara.aladdin.model.server;        
    exports com.xelara.aladdin.model.text;        
    
    exports com.xelara.aladdin.model.simpleunit;
    exports com.xelara.aladdin.model.simpleunit.model;
	
    exports com.xelara.aladdin.index;
    exports com.xelara.aladdin.index.model;
    
	requires transitive com.xelara.structure;
	
}