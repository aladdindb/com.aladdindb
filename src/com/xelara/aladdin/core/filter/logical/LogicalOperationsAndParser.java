package com.xelara.aladdin.core.filter.logical;

import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.core.filter.FilterFactory;
import com.xelara.aladdin.core.filter.FilterFactoryDefault;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;


public class LogicalOperationsAndParser < UDM extends DataModel< UDM > > extends DataParser< LogicalOperationsAnd < UDM > > {

	
	private final  FilterFactory < UDM > factory; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOperationsAndParser( FilterFactory < UDM > factory ) { 
		
		super( FilterFactoryDefault.FILTER_LOGICAL_AND );
		
		this.factory = factory;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalOperationsAnd < UDM > newModel() {
		return new LogicalOperationsAnd< UDM >( this.factory );
	}

	@Override
	public LogicalOperationsAnd < UDM > toModel( SnPoint src, LogicalOperationsAnd < UDM > target ) {
		
		src.children.forEach( node -> {
			this.factory.createFilter( node, target.filterList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalOperationsAnd < UDM > src, SnPoint target ) {

		var array = src.filterList.toArray( new Filter[ src.filterList.size() ] );
//		 
		for( var filter : array ) {
			var node = filter.createParser().toNode( filter );
			target.children.add( node );  
		}
//		
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	
}
